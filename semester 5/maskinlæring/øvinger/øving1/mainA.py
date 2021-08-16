import torch
import matplotlib.pyplot as plt

"""
# Observed/training input and output
x_train = torch.tensor([1, 1.5, 2, 3, 4, 5, 6]).reshape(-1, 1)  # x_train = [[1], [1.5], [2], [3], [4], [5], [6]]
y_train = torch.tensor([5, 3.5, 3, 4, 3, 1.5, 2]).reshape(-1, 1)  # y_train = [[5], [3.5], [3], [4], [3], [1.5], [2]]
"""

# new observed/training input and output
import csv

x_arr = []
y_arr = []

with open('length_weight.csv') as csv_file:  # read dataset from file
    csv_reader = csv.reader(csv_file, delimiter=',')
    line_count = 0

    for row in csv_reader:

        if line_count == 0:
            line_count += 1
        else:
            x_arr.append(float(row[0]))
            y_arr.append(float(row[1]))

x_train = torch.tensor(x_arr, device="cuda").reshape(-1, 1)
y_train = torch.tensor(y_arr, device="cuda").reshape(-1, 1)


class LinearRegressionModel:
    def __init__(self):
        # Model variables
        # self.W = torch.tensor([[0.0]], requires_grad=True)  # requires_grad enables calculation of gradients
        # self.b = torch.tensor([[0.0]], requires_grad=True)

        self.W = torch.tensor([[0.0]], requires_grad=True, device="cuda")  # requires_grad enables calculation of gradients
        self.b = torch.tensor([[0.0]], requires_grad=True, device="cuda")

    # Predictor
    def f(self, x):
        return x @ self.W + self.b  # @ corresponds to matrix multiplication

    # Uses Mean Squared Error
    def loss(self, x, y):
        return torch.mean(torch.square(self.f(
            x) - y))  # Can also use torch.nn.functional.mse_loss(self.f(x), y) to possibly increase numberical stability


model = LinearRegressionModel()

# Optimize: adjust W and b to minimize loss using stochastic gradient descent
optimizer = torch.optim.SGD([model.b, model.W], 0.0001)
for epoch in range(10000):
    model.loss(x_train, y_train).backward()  # Compute loss gradients
    optimizer.step()  # Perform optimization by adjusting W and b,
    # similar to:
    # model.W -= model.W.grad * 0.01
    # model.b -= model.b.grad * 0.01

    optimizer.zero_grad()  # Clear gradients for next step

# Print model variables and loss
print("W = %s, b = %s, loss = %s" % (model.W, model.b, model.loss(x_train, y_train)))

# Visualize result


plt.plot(x_train.detach().cpu(), y_train.detach().cpu(), 'o', label='$(\hat x^{(i)},\hat y^{(i)})$')
plt.xlabel('x')
plt.ylabel('y')
x = torch.tensor([[torch.min(x_train)], [torch.max(x_train)]]).cuda() # x = [1, 6]
plt.plot(x.cpu(), model.f(x).cpu().detach(), label='$y = f(x) = xW+b$')
plt.legend()
plt.show()
