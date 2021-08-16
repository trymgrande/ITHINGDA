import torch
import matplotlib.pyplot as plt
import numpy as np
from matplotlib import cm
import random

x_train = torch.tensor([[0.0, 0.0], [0.0, 1.0], [1.0, 0.0], [1.0, 1.0]], device="cuda")
z_train = torch.tensor([0.0, 1.0, 1.0, 0.0], device="cuda").reshape(-1, 1)



class XorModel:
    def __init__(self):
        self.W1 = torch.tensor(
            [[random.uniform(-1, 1), random.uniform(-1, 1)], [random.uniform(-1, 1), random.uniform(-1, 1)]],
            requires_grad=True, device="cuda")
        self.W2 = torch.tensor([[random.uniform(-1, 1)], [random.uniform(-1, 1)]], requires_grad=True, device="cuda")
        self.b1 = torch.tensor([random.uniform(-1, 1), random.uniform(-1, 1)], requires_grad=True, device="cuda")
        self.b2 = torch.tensor([[random.uniform(-1, 1)]], requires_grad=True, device="cuda")

    def f(self, x):
        return self.f2(self.f1(x))

    def f1(self, x):
        z = x @ self.W1 + self.b1
        return torch.sigmoid(z)

    def f2(self, r):
        z = r @ self.W2 + self.b2
        return torch.sigmoid(z)

    def loss(self, x, z):
        return torch.nn.functional.binary_cross_entropy(self.f(x), z)


model = XorModel()

optimizer = torch.optim.SGD([model.b1, model.b2, model.W1, model.W2], 15)

counter = 0
epochs = 50000
while counter < epochs:
    model.loss(x_train, z_train).backward()
    optimizer.step()
    optimizer.zero_grad()
    counter += 1

    if counter % 5000 == 0:
        # print("Runs : %s, " "W = %s, b = %s, loss = %s" % (counter, model.W, model.b1, model.loss(x_train, z_train)))
        print("Progress: %s%%, W1 = %s, W2 = %s b1 = %s, b2 = %s, loss = %s" % (round((counter / epochs)*100, 1), model.W1, model.W2, model.b1, model.b2, model.loss(x_train, z_train)))



# Visualize result
fig = plt.figure()
ax = fig.gca(projection='3d')

X = np.arange(0, 1.1, 0.02)
Y = np.arange(0, 1.1, 0.02)
Z = np.empty([len(X), len(Y)], dtype=np.double)
for t in range(len(X)):
    for r in range(len(Y)):
        Z[t, r] = float(model.f(torch.tensor([float(X[t]), float(Y[r])], device="cuda")))

X, Y = np.meshgrid(X, Y)
surf = ax.plot_wireframe(X, Y, np.array(Z), cmap=cm.coolwarm, alpha=0.2)

xer = [float(x[0]) for x in x_train]
yer = [float(x[1]) for x in x_train]
ax.scatter(xer, yer, z_train.cpu())

float(model.f(torch.tensor([1.0, 0.0], device="cuda")))

# Make legend, set axes limits and labels
# ax.legend()
ax.set_xlim(0, 1)
ax.set_ylim(0, 1)
ax.set_zlim(0, 1)
ax.set_xlabel('X')
ax.set_ylabel('Y')
ax.set_zlabel('Z')

plt.show()