import torch
import matplotlib.pyplot as plt
import math

# NOT Operator model

# new observed/training input and output
x_arr = [0.0, 1.0]
y_arr = [1.0, 0.0]

x_train = torch.tensor(x_arr, device="cuda").reshape(-1, 1)
y_train = torch.tensor(y_arr, device="cuda").reshape(-1, 1)

def s(z):
    return 1/(1 + math.e**(-z))

class NotModel:
    def __init__(self):
        # Model variables
        self.W = torch.tensor([[0.0]], requires_grad=True, device="cuda")  # requires_grad enables calculation of gradients
        self.b = torch.tensor([[0.0]], requires_grad=True, device="cuda")

    # Predictor
    def f(self, x):
        return torch.sigmoid(x @ self.W + self.b)

    # Uses cross entropy
    def loss(self, x, y):
        return torch.nn.functional.binary_cross_entropy(self.f(x), y)
        # return torch.mean(torch.square(self.f(x) - y))  # Can also use torch.nn.functional.mse_loss(self.f(x), y) to possibly increase numberical stability


model = NotModel()

# Optimize: adjust W and b to minimize loss using stochastic gradient descent
optimizer = torch.optim.SGD([model.b, model.W], 15)
for epoch in range(10000):
    model.loss(x_train, y_train).backward()  # Compute loss gradients
    optimizer.step()  # Perform optimization by adjusting W and b,
    optimizer.zero_grad()  # Clear gradients for next step

# Print model variables and loss
print("W = %s, b = %s, loss = %s" % (model.W, model.b, model.loss(x_train, y_train)))

plt.plot(x_train.detach().cpu(), y_train.detach().cpu(), 'o', label='$(\hat x^{(i)},\hat y^{(i)})$')
plt.xlabel('x')
plt.ylabel('y')
# x = torch.tensor([[torch.min(x_train)], [torch.max(x_train)]]).cuda()
x = torch.arange(torch.min(x_train), torch.max(x_train), 0.001, device="cuda").reshape(-1, 1)
plt.plot(x.cpu(), model.f(x).cpu().detach(), label='f(x)')
plt.legend()
plt.show()
