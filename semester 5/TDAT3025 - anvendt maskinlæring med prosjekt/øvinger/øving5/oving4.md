# Øving 4 Matte 2020

**Hans Krisitan Granli, Torje Thorkildsen, Trym Grande, Thomas Bjerke**

## Sauer 6.1

### 1)

$$ y(t) = t \sin t$$

$$ y'(t) = \sin(t) + t\cos(t) $$

$$ y''(t) = \cos(t) + \cos(t) - t\sin(t)$$

$$ \Rightarrow y''(t) = 2\cos(t) - t\sin(t)$$

#### a)

$$ y + t^2\cos(t) = ty'$$

**Left side:**

$$ t\sin(t) + t^2\cos(t)$$

**Right side:**

$$ t*y \Rightarrow t(\sin(t) + t\cos(t))$$

$$ t\sin(t) + t^2\cos(t)$$

<center>RS = LS</center>

____

#### b)

$$ y'' = 2\cos(t) - y(c)t(y''+y) = 2y'-2\sin(t) $$

**Left side:**

$$ y'' = 2\cos(t) - t\sin(t) $$

**Right side:**

$$ 2\cos(t) - y$$

$$ 2\cos(t) - t\sin(t) $$

<center>RS = LS </center>

____

#### c)

 $$ t(y'' + y) = 2y' - 2\sin(t)$$

 **Left side:**

 $$ t(y'' + y) $$

 $$ 2t\cos(t) - t^2\sin(t) + t^2\sin(t)$$

 $$ 2t\cos(t) $$

 **Right side:**

 $$ 2y' - 2\sin(t) $$

 $$ 2\sin(t) + 2t\cos(t) - 2\sin(t) $$

 $$ 2t\cos(t) $$

 <center> RS = LS </center>

____

### 3b)

 $$ y(0) = 1 $$

 $$ y' = t^2 y $$

 $$ \frac{dy}{dt} = t^2y | \frac{dt}{y}$$

 $$ \frac{dy}{y} = t^2dt$$

 $$ \int y^{-1}dy = \int t^2dt$$

 $$ \ln y + C= \frac{1}{3}t^3 + C | e $$

 $$ e^{\ln y + C} = e^{t/3+C}$$

 $$ y = e^{t/3}+c$$

 $$ y(0) = 1 $$

 $$ y(0) = e^{0} + C$$

 $$ y(0) = 1 + C$$ 

 $$ y(t) = e^{t/3} $$

____

### 5b)

$$ h = 1/4 $$

$$ dF = [0,1] $$

$$ y' = t^2y$$

$$ w_0 = y_0 $$

$$ w_{i+1} = w_i + h(w_it^2_i) $$



| Step | $t_i$ | $w_i$  | $y_i $ | $e_i$   |
| ---- | ----- | ------ | ------ | ------- |
| 1 | 0.25 |1.000000 | 1.086904 |0.086904 |
| 2 | 0.50 |1.015625 | 1.181360 |0.165735 |
| 3 | 0.75 |1.079102 | 1.284025 |0.204924 |
| 4 | 1.00 |1.230850 | 1.395612 |0.164762 |


____



### CP1b)

```python {cmd="python3"} 

import math as m
import numpy as np 

h = .1
w, t = 1., np.arange(.0, 1., h)

for i in t:
    y, w = m.e**((i+h)**3/3), w + h*w*i**2
    print("t = {:.2f}, W = {:^5f}, y = {:^5f},  err = {:>5f}"
    .format((i+h), w, y, abs(w-y)))

```

____

## Sauer 6.2

### 1a)

$$ y' = t $$

$$ y(0) = 1 $$

$$ h = 1/4 $$

$$ y(t) = \frac{t^2}{2} + 1$$

$$ w_{i+1} = w_i + \frac{h}{2}(w_it +( hw_it + hw_it^2)) $$

$$ w_{i+1} = w_i + \frac{h}{2}(2t_i+h)$$


| step | $t_i$ | $w_i$    | $y_i$    | $e_i$    |
| ---- | ----- | -------- | -------- | -------- |
| 1    | 0.25  | 1.031250 | 1.000000 | 0.031250 |
| 2    | 0.50  | 1.125000 | 1.031250 | 0.093750 |
| 3    | 0.75  | 1.281250 | 1.125000 | 0.156250 |
| 4    | 1.00  | 1.500000 | 1.281250 | 0.218750 |

____

### CP1a)


```python {cmd="python3"} 
import numpy as np
h = .1
t, w, y =  np.arange(0., 1., h), 1., 1
for i in t: 
    w, y = w + h/2 * (2*i + h), ((i+h)**2)/2 + 1
    print("t = {:.2f}, W = {:^5f}, y = {:^5f},  err = {:>5f}"
    .format((i+h), w, y, abs(w-y)))

```