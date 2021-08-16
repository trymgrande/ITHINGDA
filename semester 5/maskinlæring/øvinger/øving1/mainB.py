import torch
import matplotlib.pyplot as plt
from mpl_toolkits import mplot3d
import csv

import numpy as np

dayInput = []
lengthInput = []
weightInput = []

# reading file attributes and converting to lists
with open('day_length_weight.csv') as csv_file:
    csv_reader = csv.reader(csv_file, delimiter=',')
    line_nr = 0

    for row in csv_reader:
        if line_nr == 0:
            line_nr = 1
        else:
            dayInput.append(float(row[0]))
            lengthInput.append(float(row[1]))
            weightInput.append(float(row[2]))

# Observed/training input and output
day_train = torch.tensor(dayInput).reshape(-1, 1)
length_train = torch.tensor(lengthInput).reshape(-1, 1)
weight_train = torch.tensor(weightInput).reshape(-1, 1)


class LinearRegressionModel:
    def __init__(self):
        # Model variables
        self.W1 = torch.tensor([[0.0]], requires_grad=True)  # requires_grad enables calculation of gradients
        self.W2 = torch.tensor([[0.0]], requires_grad=True)
        self.b = torch.tensor([[0.0]], requires_grad=True)

    # Predictor
    def f(self, x1, x2):
        return (x1 @ self.W1) + (x2 @ self.W2) + self.b  # @ corresponds to matrix multiplication

    # Uses Mean Squared Error
    def loss(self, x1, x2, y):
        return torch.mean(torch.square(self.f(x1, x2) - y))


model = LinearRegressionModel()

# Optimize: adjust W and b to minimize loss using stochastic gradient descent
optimizer = torch.optim.SGD([model.b, model.W1, model.W2], 0.0001)

# allowing for long run to test optimal epoch value
for epoch in range(10000):
    model.loss(length_train, weight_train, day_train).backward()  # Compute loss gradients
    optimizer.step()  # Perform optimization by adjusting W and b,
    optimizer.zero_grad()  # Clear gradients for next step

# Print model variables and loss
print("W1 = %s, W2 = %s, b = %s, loss = %s" % (float(model.W1), float(model.W2), float(model.b), float(model.loss(length_train, weight_train, day_train))))

# Visualize result
fig = plt.figure()
ax = fig.gca(projection='3d')

# Plot a sin curve using the x and y axes.
x = np.linspace(0, 2500)
y = np.linspace(0, 100)
X, Y = np.meshgrid(x, y)

w = model.W1.cpu().detach()
w2 = model.W2.cpu().detach()
bb = model.b.cpu().detach()


def plane(X, Y):
    return float(w) * X + float(w2) * Y + bb.item()


Z = plane(X, Y)  # w[0] * X + w[1] * Y + bb

ax.plot_surface(X, Y, Z, alpha=0.5)

xer = [float(x[0]) for x in length_train]
yer = [float(x[0]) for x in weight_train]
ax.scatter(xer, yer, day_train.cpu())

# Make legend, set axes limits and labels
# ax.legend()
ax.set_xlim(0, 100)
ax.set_ylim(0, 30)
ax.set_zlim(0, 2500)
ax.set_xlabel('X')
ax.set_ylabel('Y')
ax.set_zlabel('Z')


plt.show()
