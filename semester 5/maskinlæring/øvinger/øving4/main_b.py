import torch
import torch.nn as nn


class LongShortTermMemoryModel(nn.Module):
    def __init__(self, encoding_size):
        super(LongShortTermMemoryModel, self).__init__()

        self.lstm = nn.LSTM(encoding_size, 128)  # 128 is the state size
        self.dense = nn.Linear(128, encoding_size)  # 128 is the state size

    def reset(self):  # Reset states prior to new input sequence
        zero_state = torch.zeros(1, 1, 128)  # Shape: (number of layers, batch size, state size)
        self.hidden_state = zero_state
        self.cell_state = zero_state

    def logits(self, x):  # x shape: (sequence length, batch size, encoding size)
        out, (self.hidden_state, self.cell_state) = self.lstm(x, (self.hidden_state, self.cell_state))
        return self.dense(out.reshape(-1, 128))

    def f(self, x):  # x shape: (sequence length, batch size, encoding size)
        # print('f: ', torch.softmax(self.logits(x), dim=1))
        return torch.softmax(self.logits(x), dim=1)

    def loss(self, x, y):  # x shape: (sequence length, batch size, encoding size), y shape: (sequence length, encoding size)
        return nn.functional.cross_entropy(self.logits(x), y.argmax(1))


char_encodings = [
    [1., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0.],  # 0, ' '
    [0., 1., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0.],  # 1, 'h'
    [0., 0., 1., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0.],  # 2, 'a'
    [0., 0., 0., 1., 0., 0., 0., 0., 0., 0., 0., 0., 0.],  # 3, 't'
    [0., 0., 0., 0., 1., 0., 0., 0., 0., 0., 0., 0., 0.],  # 4, 'r'
    [0., 0., 0., 0., 0., 1., 0., 0., 0., 0., 0., 0., 0.],  # 5, 'c'
    [0., 0., 0., 0., 0., 0., 1., 0., 0., 0., 0., 0., 0.],  # 6, 'f'
    [0., 0., 0., 0., 0., 0., 0., 1., 0., 0., 0., 0., 0.],  # 7, 'l'
    [0., 0., 0., 0., 0., 0., 0., 0., 1., 0., 0., 0., 0.],  # 8, 'm'
    [0., 0., 0., 0., 0., 0., 0., 0., 0., 1., 0., 0., 0.],  # 9, 'p'
    [0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 1., 0., 0.],  # 10, 's'
    [0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 1., 0.],  # 11, 'o'
    [0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 1.]  # 12, 'n'
]

char_encoding_size = len(char_encodings)

index_to_char = [' ', 'h', 'a', 't', 'r', 'c', 'f', 'l', 'm', 'p', 's', 'o', 'n']


emoji_encodings = [
    [1., 0., 0., 0., 0., 0., 0.],  # 'hat', 0
    [0., 1., 0., 0., 0., 0., 0.],  # 'rat', 1
    [0., 0., 1., 0., 0., 0., 0.],  # 'cat', 2
    [0., 0., 0., 1., 0., 0., 0.],  # 'flat', 3
    [0., 0., 0., 0., 1., 0., 0.],  # 'matt', 4
    [0., 0., 0., 0., 0., 1., 0.],  # 'cap', 5
    [0., 0., 0., 0., 0., 0., 1.],  # 'son', 6
]
emoji_encoding_size = len(emoji_encodings)

index_to_emoji = ['🎩', '🐀', '🐱', '🥿', '👨', '🎓', '👦']


def generate_x_train_element(word):
    def split(word):
        return [char for char in word]
    characters = split(word)
    out = []
    for character in characters:
        out.append([char_encodings[index_to_char.index(character)]])
    return out


x_train = torch.tensor([generate_x_train_element("hat "),
                        generate_x_train_element("rat "),
                        generate_x_train_element("cat "),
                        generate_x_train_element("flat"),
                        generate_x_train_element("matt"),
                        generate_x_train_element("cap "),
                        generate_x_train_element("son ")
])


y_train = torch.tensor([
    [emoji_encodings[0], emoji_encodings[0], emoji_encodings[0], emoji_encodings[0]],
    [emoji_encodings[1], emoji_encodings[1], emoji_encodings[1], emoji_encodings[1]],
    [emoji_encodings[2], emoji_encodings[2], emoji_encodings[2], emoji_encodings[2]],
    [emoji_encodings[3], emoji_encodings[3], emoji_encodings[3], emoji_encodings[3]],
    [emoji_encodings[4], emoji_encodings[4], emoji_encodings[4], emoji_encodings[4]],
    [emoji_encodings[5], emoji_encodings[5], emoji_encodings[5], emoji_encodings[5]],
    [emoji_encodings[6], emoji_encodings[6], emoji_encodings[6], emoji_encodings[6]]
])


model = LongShortTermMemoryModel(char_encoding_size)

optimizer = torch.optim.RMSprop(model.parameters(), 0.0005)


def test(word):
    model.reset()
    for character in word:
        y = model.f(torch.tensor(generate_x_train_element(character)))
    out = word + ": " + index_to_emoji[y.argmax(1)]
    print(out)


for epoch in range(500):
    for i in range(x_train.size()[0]):
        model.reset()
        model.loss(x_train[i], y_train[i]).backward()
        optimizer.step()
        optimizer.zero_grad()

    if epoch % 10 == 9:
        test("hat")
        test("rt")
        test("cat")
        test("flat")
        test("matt")
        test("cap")
        test("son")
        print()
