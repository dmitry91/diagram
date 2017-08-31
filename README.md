To start you need to create a database 'diagram'
and crete table
CREATE TABLE `user` (
  `id` mediumint(9) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8

Update Class 'DataConnect.java' 
DriverManager.getConnection("jdbc:mysql://localhost:3306/diagram", "root", "password");
The project ran on Tomcat 8