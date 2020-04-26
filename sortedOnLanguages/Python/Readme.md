# Creating multidimensional arrays:
0. As a list:
```
sX_train = [[[[0 for a in range(n_trials)] for b in range(n_epochs)] for c in range(n_channels)] for d in range(n_samples)]
```
1. As a numpy array:
```
v_swarm_iris = np.zeros((nr_of_classes_iris,len(main.sample_iris[0])), dtype=float)
some_array = np.zeros((2,3,4,5), dtype=float)
```