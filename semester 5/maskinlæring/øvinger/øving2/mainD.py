import torch
import matplotlib.pyplot as plt
import torchvision

# Load observations from the mnist dataset. The observations are divided into a training set and a test set
mnist_train = torchvision.datasets.MNIST('./data', train=True, download=True)
x_train = mnist_train.data.reshape(-1, 784).float()  # Reshape input
#y_train = torch.zeros((mnist_train.targets.shape[0], 10))  # Create output tensor
y_train = torch.tensor(mnist_train.targets, dtype=torch.long)
#y_train[torch.arange(mnist_train.targets.shape[0]), mnist_train.targets] = 1  # Populate output

mnist_test = torchvision.datasets.MNIST('./data', train=False, download=True)
x_test = mnist_test.data.reshape(-1, 784).float()  # Reshape input
y_test = torch.zeros((mnist_test.targets.shape[0], 10))  # Create output tensor
y_test[torch.arange(mnist_test.targets.shape[0]), mnist_test.targets] = 1  # Populate output


class DigitModel:
    def __init__(self):
        self.W = torch.ones(784,10 , requires_grad=True)
        self.b = torch.ones(1, 10, requires_grad=True)

    def f(self, x):
        return torch.nn.functional.softmax((x @ self.W + self.b), dim=1)

    def loss(self, x, y):
        return torch.nn.functional.cross_entropy(self.f(x), y)

    def accuracy(self, x, y):
        return torch.mean(torch.eq(self.f(x).argmax(1), y.argmax(1)).float())


model = DigitModel()

optimizer = torch.optim.SGD([model.b, model.W], 0.1)

counter = 0
epochs = 100
while counter < epochs:
# while True:
    model.loss(x_train, y_train).backward()
    optimizer.step()
    optimizer.zero_grad()
    counter += 1

    if counter % 10 == 0:
        # print("Runs : %s, " "W = %s, b = %s, loss = %s" % (counter, model.W, model.b1, model.loss(x_train, z_train)))
        # print("Progress: %s, Accuracy = %.2f%%, W = %s, b = %s, loss = %s" % (round((counter / epochs), 1), model.accuracy(x_test, y_test).item()*100, model.W, model.b, model.loss(x_train, y_train)))
        print("Count : %s   loss = %.4f   Accuracy = %.2f%%" % (counter, model.loss(x_train, y_train).item(), model.accuracy(x_test, y_test).item()*100))
        if model.accuracy(x_test, y_test).item()*100 > 90:
            print("Accuracy > 90%%")
            break

# Visualize result

for i in range(10):
    plt.subplot(2,5,i+1)
    plt.imshow(model.W[:,i].detach().numpy().reshape(28,28))
    plt.title("W: {}".format(i))
    plt.xticks([])
    plt.yticks([])

plt.show()
