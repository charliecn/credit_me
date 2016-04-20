
import sqlite3 as lite
import sys

con = None



con = lite.connect('creditMe.sqlite3')
        
cur = con.cursor()    

cur.execute("INSERT INTO eatery VALUES('1','Andrews Commons', 'North', 540, 1440,'They say some eat Andrews 24/7.')")

cur.execute("INSERT INTO eatery VALUES('2','Blueroom', 'Center', 330, 1140,'Best known to campus visitors :)')")

cur.execute("INSERT INTO eatery VALUES('3','Josiah''s', 'South', 960, 1440,'We talk business there after party.')")

cur.execute("INSERT INTO eatery VALUES('4','Ivy Room', 'Center', 540,1440,'Smoothie loved by many!')")


cur.execute("INSERT INTO eatery VALUES('5','Ratty', 'Center', 330,1050,'Like or hate, you still go')")

cur.execute("INSERT INTO eatery VALUES('6','V-Dub', 'North', 330,1050,'Home to Pembroke denizens')")
con.commit()