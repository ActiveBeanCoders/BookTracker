BookTracker
===========

Tracks Authors/Books 

Due to the fact that I can't remember what I've read and end up buying the same book twice I now log Authors I like and their books to keep track of what I own and what I've read.

A report can be emailed of unread/unowned books to take to the bookstore.


Uses:
-----
Spring core
Spring MVC
Spring Security
Maven
MySQL

Limitations:
------------
It is designed for only 2 users (my household) to share booklists.


TO DOs:
-------
1.Security: the current passwords are hardcoded in an xml file and created with a basic command

	echo -n "passw0rd" | openssl sha1
	
  This should probably get generated and stored in a user table.

2.The rendered books and authors move down the page as more are added leaving a mass of space at the top of the page. Fix

3.Runs under Safari but not Chrome.  