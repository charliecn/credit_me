

result = set()

with open("location_corpa") as file:
	content = file.readlines()

	for line in content:
		result.add(line)

with open("location_corpa_no_duplicate",'w') as f:
	for place in result:
		f.write("\""+place.replace('\n','", '))