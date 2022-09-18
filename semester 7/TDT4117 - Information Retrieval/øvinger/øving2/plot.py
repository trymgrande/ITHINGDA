from matplotlib import pyplot as plt


x = [0, 0, 0, 0, 0.1, 0.1, 0.1, 0.1, 0.2, 0.2, 0.3, 0.3, 0.3, 0.3, 0.4, 0.4, 0.4, 0.4]
y = [0.6, 0.6, 0.6, 0.6, 0.6, 0.6, 0.6, 0.6, 0.6, 0.6, 0.57, 0.57, 0.57, 0.57, 0.5, 0.5, 0.5, 0.5]

plt.xlabel("recall")
plt.ylabel("precision")

plt.plot(x, y)
plt.show()