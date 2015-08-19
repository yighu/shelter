#mysql --host=2001:4860:4864:1:995c:8786:cbb8:4bd0 --user=yighu --password=abcd123

#1.on your machine run this url to find your ip address: http://test-ipv6.com 
#2. on google account, authorise access app db(google cloud sql) to the ip address found above
#3. add app username(yighu) and password=abcd123
#4 run the following command 
#request and ipv4 address and used like below:
#the shelterapp instance
#mysql --host=173.194.224.162 --user=yighu --password=abcd123
#the colcsb (prod)instance
mysql --host=173.194.107.210 --user=yighu --password=abcd123
#5 after finish, remove the url so i am not charged
