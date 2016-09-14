f = open('rattymenu','r')

content = f.readlines();

food = set();

to_w = open('rattymenu_purged','w')

for line in content:

	if line in food:
		print line
	else:
		food.add(line)
		to_w.write(line)