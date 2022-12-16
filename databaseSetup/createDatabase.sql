CREATE DATABASE IF NOT EXISTS `stompydb`;
USE `stompydb`;

CREATE TABLE `projects` (
  `project_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `created_on` DATETIME DEFAULT NULL,
  `version` varchar(10),
  PRIMARY KEY (`project_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;


CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `email` varchar(256) NOT NULL,
  `password` varchar(256) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;

CREATE TABLE `bugs_users` (
  `user_id` int(11) NOT NULL,
  `bug_id` int(11) NOT NULL
);

CREATE TABLE `bugs` (
	`bug_id` int(11) NOT NULL AUTO_INCREMENT,
    `project_id` int(11) NOT NULL,
    `summary` varchar(255) NOT NULL,
    `description` varchar(3000),
    `created_user_id` int(11) NOT NULL,
    `created_on` datetime NOT NULL,
    `priority_id` int(11) NOT NULL,
    `bug_status_id` int(11) NOT NULL,
    `assigned_user_id` int(11) NOT NULL,
    `bug_type_id` int(11) NOT NULL,
    `bug_version` varchar(12) NOT NULL,
    `resolution_version` varchar(12) NOT NULL,
    `resolution_date` datetime DEFAULT NULL,
    PRIMARY KEY (`bug_id`)
);

CREATE TABLE `roles` (
	`role_id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(8) NOT NULL,
    PRIMARY KEY (`role_id`)
);

CREATE TABLE `priorities` (
	`priority_id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(8) NOT NULL,
    PRIMARY KEY (`priority_id`)
);


CREATE TABLE `bug_status` (
	`bug_status_id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(8) NOT NULL,
    PRIMARY KEY (`bug_status_id`)
);

CREATE TABLE `bug_types` (
	`bug_type_id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(8) NOT NULL,
    PRIMARY KEY (`bug_type_id`)
);

CREATE TABLE `bug_comments` (
	`bug_comment_id` int(11) NOT NULL AUTO_INCREMENT,
    `bug_id` int(11) NOT NULL,
    `user_id` int(11) NOT NULL,
    `created_on` datetime NOT NULL,
    `content` varchar(2048) NOT NULL,
    PRIMARY KEY (`bug_comment_id`)
);

ALTER TABLE `users`
ADD FOREIGN KEY (`role_id`) REFERENCES `roles`(`role_id`);

ALTER TABLE `bugs_users`
ADD FOREIGN KEY (`user_id`) REFERENCES `users`(`user_id`);
ALTER TABLE `bugs_users`
ADD FOREIGN KEY(`bug_id`) REFERENCES `bugs`(`bug_id`);

ALTER TABLE `bugs`
ADD FOREIGN KEY (`project_id`) REFERENCES `projects`(`project_id`);
ALTER TABLE `bugs`
ADD FOREIGN KEY (`created_user_id`) REFERENCES `users`(`user_id`);
ALTER TABLE `bugs`
ADD FOREIGN KEY (`priority_id`) REFERENCES `priorities`(`priority_id`);
ALTER TABLE `bugs`
ADD FOREIGN KEY (`bug_status_id`) REFERENCES `bug_status`(`bug_status_id`);
ALTER TABLE `bugs`
ADD FOREIGN KEY (`bug_type_id`) REFERENCES `bug_types`(`bug_type_id`);

ALTER TABLE `bug_comments`
ADD FOREIGN KEY (`bug_id`) REFERENCES `bugs`(`bug_id`);
ALTER TABLE `bug_comments`
ADD FOREIGN KEY (`user_id`) REFERENCES `users`(`user_id`);

INSERT INTO `roles` VALUES
	(1, 'user'),
    (2, 'dev'),
    (3, 'admin');

INSERT INTO `priorities` VALUES
	(1, 'critical'),
    (2, 'high'),
    (3, 'routine'),
    (4, 'low');

INSERT INTO `bug_types` VALUES
	(1, 'bug'),
    (2, 'feature');

INSERT INTO `bug_status` VALUES
	(1, 'new'),
    (2, 'open'),
    (3, 'assigned'),
    (4, 'resolved');