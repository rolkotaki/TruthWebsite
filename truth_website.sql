-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Hoszt: 127.0.0.1
-- Létrehozás ideje: 2016. Aug 18. 18:14
-- Szerver verzió: 5.6.17
-- PHP verzió: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Adatbázis: `truth_website`
--

CREATE DATABASE IF NOT EXISTS truth_website;
USE truth_website;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához USERS
--

CREATE TABLE IF NOT EXISTS users (
	user_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	username VARCHAR(25) NOT NULL,
	email_id VARCHAR(100) NOT NULL,
	gender TINYINT(1) NOT NULL,
	birth_year SMALLINT(4) UNSIGNED NOT NULL,
	password VARCHAR(300) NOT NULL,
	PRIMARY KEY (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Tábla szerkezet ehhez a táblához CATEGORIES
--

CREATE TABLE IF NOT EXISTS categories (
	category_id TINYINT UNSIGNED NOT NULL AUTO_INCREMENT,
	category_name VARCHAR(25) NOT NULL,
	PRIMARY KEY (category_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Tábla szerkezet ehhez a táblához NEWS   meg majd kepeket-videokat hozzaadni
--

CREATE TABLE IF NOT EXISTS articles (
	article_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	article_title VARCHAR(300) NOT NULL,
	article_text TEXT,
	article_date DATETIME,
	article_source VARCHAR(50),
	article_image_path VARCHAR(300),
	PRIMARY KEY (article_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Tábla szerkezet ehhez a táblához ARTICLES CATEGORIES
--

CREATE TABLE IF NOT EXISTS articles_categories (
	article_id INT UNSIGNED,
	category_id TINYINT UNSIGNED,
	-- PRIMARY KEY (article_id, category_id),
	FOREIGN KEY (article_id) REFERENCES articles(article_id) ON DELETE SET NULL ON UPDATE CASCADE,
	FOREIGN KEY (category_id) REFERENCES categories(category_id) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Tábla szerkezet ehhez a táblához COMMENTS
--

CREATE TABLE IF NOT EXISTS comments (
	comment_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	comment_text VARCHAR(1010) NOT NULL,
	comment_date DATETIME NOT NULL,
	user_id INT UNSIGNED,
	article_id INT UNSIGNED,
	PRIMARY KEY (comment_id),
	FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE SET NULL ON UPDATE CASCADE,
	FOREIGN KEY (article_id) REFERENCES articles(article_id) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Tábla szerkezet ehhez a táblához LEADING ARTICLES
--

CREATE TABLE IF NOT EXISTS leading_articles (
	article_id INT UNSIGNED NOT NULL,
	main_or_not TINYINT(1) NOT NULL,
	PRIMARY KEY (article_id),
	FOREIGN KEY (article_id) REFERENCES articles(article_id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
---- Inserting admin user
--

insert into users (username, email_id, gender, birth_year, password) values ('admin', 'admin@email.hu', 1, 1991, 'cf43e029efe6476e1f7f84691f89c876818610c2eaeaeb881103790a48745b82');
commit;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
