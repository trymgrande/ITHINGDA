import torch
import matplotlib.pyplot as plt
import numpy as np

# NAND Operator model
  
# new observed/training input and output
x1_arr = [0.0, 0.0, 1.0, 1.0]
x2_arr = [0.0, 1.0, 0.0, 1.0]
y_arr = [1.0, 1.0, 1.0, 0.0]

x1_train = torch.tensor(x1_arr, device="cuda").reshape(-1, 1)
x2_train = torch.tensor(x2_arr, device="cuda").reshape(-1, 1)
y_train = torch.tensor(y_arr, device="cuda").reshape(-1, 1)


class NandModel:
    def __init__(self):
        # Model variables
        # requires_grad enables calculation of gradients
        self.W1 = torch.tensor([[0.0]], requires_grad=True, device="cuda")
        self.W2 = torch.tensor([[0.0]], requires_grad=True, device="cuda")
        self.b = torch.tensor([[0.0]], requires_grad=True, device="cuda")

    # Predictor
    def f(self, x1, x2):
        return torch.sigmoid(x1 @ self.W1 + x2 @ self.W2 + self.b)

    # Uses cross entropy
    def loss(self, x1, x2, y):
        return torch.nn.functional.binary_cross_entropy(self.f(x1, x2), y)


model = NandModel()

# Optimize: adjust W and b to minimize loss using stochastic gradient descent
optimizer = torch.optim.SGD([model.b, model.W1, model.W2], 15)
for epoch in range(1000):
    model.loss(x1_train, x2_train, y_train).backward()  # Compute loss gradients
    optimizer.step()  # Perform optimization by adjusting W and b,
    optimizer.zero_grad()  # Clear gradients for next step

# Print model variables and loss
print("W1 = %s, W2 = %s b = %s, loss = %s" % (model.W1, model.W2, model.b, model.loss(x1_train, x2_train, y_train)))

# fig = plt.figure()
# ax = fig.gca(projection='3d')
# ax.scatter(x1_train.cpu(), x2_train.cpu(), y_train.cpu())

# plt.plot(x1_train.detach().cpu(), x2_train.detach().cpu(), 'o', label='f(x)')
# plt.xlabel('x')
# plt.ylabel('y')
# x = torch.tensor([[torch.min(x_train)], [torch.max(x_train)]]).cuda()
# x = torch.arange(torch.min(x1_train), torch.max(x1_train), 0.001, device="cuda").reshape(-1, 1)
# plt.plot(x.cpu(), model.f(x).cpu().detach(), label='f(x)')
# plt.legend()
# plt.show()




W1 = model.W1.cpu().detach().numpy()
W2 = model.W2.cpu().detach().numpy()

W_init = np.array([W1[0], W2[0]])
b_init = np.array([model.b.data[0]])

fig = plt.figure("Logistic regression: the logical NAND operator")

plot1 = fig.add_subplot(111, projection='3d')

plot1_f = plot1.plot_wireframe(np.array([[]]), np.array([[]]), np.array([[]]), color="green",
                               label="$y=f(x)=\\sigma(xW+b)$")

plot1.plot(x1_train.cpu().detach().squeeze(),
           x2_train.cpu().detach().squeeze(),
           y_train.cpu().detach().squeeze(),
           'o',
           label="$(\\hat x_1^{(i)}, \\hat x_2^{(i)},\\hat y^{(i)})$",
           color="blue")

plot1_info = fig.text(0.01, 0.02, "")

plot1.set_xlabel("$x_1$")
plot1.set_ylabel("$x_2$")
plot1.set_zlabel("$y$")
plot1.legend(loc="upper left")
plot1.set_xticks([0, 1])
plot1.set_yticks([0, 1])
plot1.set_zticks([0, 1])
plot1.set_xlim(-0.25, 1.25)
plot1.set_ylim(-0.25, 1.25)
plot1.set_zlim(-0.25, 1.25)

table = plt.table(cellText=[[0, 0, 1], [0, 1, 1], [1, 0, 1], [1, 1, 0]],
                  colWidths=[0.1] * 3,
                  colLabels=["$x$", "$y$", "$f(x)$"],
                  cellLoc="center",
                  loc="lower right")

plot1_f.remove()
x1_grid, x2_grid = np.meshgrid(np.linspace(-0.25, 1.25, 10), np.linspace(-0.25, 1.25, 10))
y_grid = np.empty([10, 10], dtype=np.double)
for i in range(0, x1_grid.shape[0]):
    for j in range(0, x1_grid.shape[1]):
        tenseX = torch.tensor(float(x1_grid[i, j]), device="cuda").reshape(-1, 1)
        tenseY = torch.tensor(float(x2_grid[i, j]), device="cuda").reshape(-1, 1)
        y_grid[i, j] = model.f(tenseX, tenseY)
plot1_f = plot1.plot_wireframe(x1_grid, x2_grid, y_grid, color="green")

"""
table._cells[(1, 2)]._text.set_text("${%.1f}$" % model.f([[0, 0]]))
table._cells[(2, 2)]._text.set_text("${%.1f}$" % model.f([[0, 1]]))
table._cells[(3, 2)]._text.set_text("${%.1f}$" % model.f([[1, 0]]))
table._cells[(4, 2)]._text.set_text("${%.1f}$" % model.f([[1, 1]]))"""

fig.canvas.draw()

plt.show()
