import numpy as np
import matplotlib.pyplot as plt

data = {}
headers = []
lines = []
columns = {}

import csv
with open('out.csv', 'rb') as csvfile:
    r = csv.reader(csvfile, delimiter=',', quotechar='|', skipinitialspace=True)
    headers = r.next()
    for h in headers:
        columns[h] = []
    lines = []
    for row in r:
        line = {}
        for h, v in zip(headers, row):
            line[h] = v
            if not v in columns[h]:
                columns[h].append(v)
        lines.append(line)
    #print(lines)

split_on = 'flaw-sel'
base = 'anml-file'
target = 'generated-states'

compiled = {}
for b in columns[base]:
    compiled[b] = {}
for l in lines:
    compiled[l[base]][l[split_on]] = l[target]

#print(compiled)

plot = []
for dataset in columns[split_on]:
    print(dataset)
    d = {}
    d['name'] = dataset
    d['data'] = []
    for c in compiled:
        d['data'].append(int(compiled[c][dataset]))
    plot.append(d)

groups = []
for c in compiled:
    groups.append(c)

for l in plot:
    print(l)
print(groups)
print(columns[split_on])



fig = plt.figure()
ax = fig.add_subplot(111)
colors = ['red', 'blue', 'green', 'yellow']

## necessary variables
ind = np.arange(len(groups))                # the x locations for the groups
width = 1.0 / (len(plot)+1)                 # the width of the bars

rects = []
for i in range(len(plot)):
    p = plot[i]
    rects.append( ax.bar(ind+i*width, p['data'], width, color=colors[i], log=True) )

    

ax.set_xlim(-width,len(ind)+width)
ax.set_ylabel(target)
ax.set_title(target + ' by ' + base)
xTickMarks = groups #['Group'+str(i) for i in range(1,6)]
ax.set_xticks(ind+(1.0-width)/2)
xtickNames = ax.set_xticklabels(xTickMarks)
plt.setp(xtickNames, rotation=45, fontsize=10, ha='right')

ax.legend( rects, [ p['name'] for p in plot ] )
plt.tight_layout()
plt.show() 
