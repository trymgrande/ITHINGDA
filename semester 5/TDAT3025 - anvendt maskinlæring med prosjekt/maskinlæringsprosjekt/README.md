# Detection and classification of speed limit signs in real time

## Installation

Use the package manager [pip](https://pip.pypa.io/en/stable/) to install the following prerequisites.

```bash
pip3 install python3
pip3 install opencv==3.4.11.43
pip3 install imutils
```

## Usage


#### There are two ways of running the program:
### Use default arguments, this will use the filename "input.mp4" by default:
```sh
$python3 main.py
```

Use custom arguments: 
```sh
$python3 main.py
optional arguments:
  -h, --help            show this help message and exit
  --file_name FILE_NAME
                        Video to be analyzed
  --min_size_components MIN_SIZE_COMPONENTS
                        Min size component to be reserved
  --similitary_contour_with_circle SIMILITARY_CONTOUR_WITH_CIRCLE
                        Similarly to a circle
```

## Result
[output.avi](./output.avi)


## Contact

### trymg@stud.ntnu.no
### thombje@stud.ntnu.no