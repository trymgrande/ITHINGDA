import torch
import matplotlib.pyplot as plt
import random
import numpy as np
import matplotlib.cm as cm

# XOR Operator model

# new observed/training input and output
x1_arr = [0.0, 0.0, 1.0, 1.0]
x2_arr = [0.0, 1.0, 0.0, 1.0]
y_arr = [0.0, 1.0, 1.0, 0.0]

x1_train = torch.tensor(x1_arr, device="cuda").reshape(-1, 1)
x2_train = torch.tensor(x2_arr, device="cuda").reshape(-1, 1)
y_train = torch.tensor(y_arr, device="cuda").reshape(-1, 1)


class XorModel:
    def __init__(self):
        # Model variables
        # requires_grad enables calculation of gradients
        self.W1 = torch.tensor([[random.uniform(-1, 1)]], requires_grad=True, device="cuda")
        self.W2 = torch.tensor([[random.uniform(-1, 1)]], requires_grad=True, device="cuda")
        self.b1 = torch.tensor([[random.uniform(-1, 1)]], requires_grad=True, device="cuda")
        self.b2 = torch.tensor([[random.uniform(-1, 1)]], requires_grad=True, device="cuda")

    # Predictor
    def f(self, x1, x2):
        return model.f2(model.f1(x1, x2))

    def f1(self, x1, x2):
        return torch.sigmoid(x1 @ self.W1 + x2 @ self.W1 + self.b1)

    def f2(self, f1):
        return torch.sigmoid(f1 @ self.W2 + self.b2)


    # Uses cross entropy
    def loss(self, x1, x2, y):
        return torch.nn.functional.binary_cross_entropy(self.f(x1, x2), y)


model = XorModel()

# Optimize: adjust W and b to minimize loss using stochastic gradient descent
optimizer = torch.optim.SGD([model.b1, model.b2, model.W1, model.W2], 15)
epochs = 10000
for epoch in range(epochs):
    if (epoch % 5000) == True:
        print("Progress: %s%%, W1 = %s, W2 = %s b1 = %s, b2 = %s, loss = %s" % (round((epoch / epochs)*100, 1), model.W1, model.W2, model.b1, model.b2, model.loss(x1_train, x2_train, y_train)))

    model.loss(x1_train, x2_train, y_train).backward()  # Compute loss gradients
    optimizer.step()  # Perform optimization by adjusting W and b,
    optimizer.zero_grad()  # Clear gradients for next step

# visualize result
fig = plt.figure()
ax = fig.gca(projection='3d')

X = np.arange(0, 1.1, 0.02)
Y = np.arange(0, 1.1, 0.02)
Z = np.empty([len(X), len(Y)], dtype=np.double)
for t in range(len(X)):
    for r in range(len(Y)):
        Z[t, r] = float(model.f(torch.tensor([float(X[t])], device="cuda"), torch.tensor([float(Y[r])], device="cuda")))

X, Y = np.meshgrid(X, Y)
surf = ax.plot_wireframe(X, Y, np.array(Z), cmap=cm.coolwarm, alpha=0.2)

# xer = [float(x[0]) for x in x1_train]
# yer = [float(x[1]) for x in x2_train]
ax.scatter(x1_train.cpu(), x2_train.cpu(), y_train.cpu())

float(model.f(torch.tensor([1.0], device="cuda"), torch.tensor([0.0], device="cuda")))

# Make legend, set axes limits and labels
# ax.legend()
ax.set_xlim(0, 1)
ax.set_ylim(0, 1)
ax.set_zlim(0, 1)
ax.set_xlabel('X')
ax.set_ylabel('Y')
ax.set_zlabel('Z')



plt.show()
