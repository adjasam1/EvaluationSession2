-- phpMyAdmin SQL Dump
-- version 4.6.6deb4
-- https://www.phpmyadmin.net/
--
-- Client :  localhost:3306
-- Généré le :  Mer 14 Novembre 2018 à 12:05
-- Version du serveur :  10.1.26-MariaDB-0+deb9u1
-- Version de PHP :  7.0.30-0+deb9u1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `bd-apprenants`
--

-- --------------------------------------------------------

--
-- Structure de la table `ACTIVITE`
--

CREATE TABLE `ACTIVITE` (
  `id` int(11) NOT NULL,
  `nom` varchar(50) COLLATE utf8_roman_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_roman_ci;

--
-- Contenu de la table `ACTIVITE`
--

INSERT INTO `ACTIVITE` (`id`, `nom`) VALUES
(1, 'Programmer en java'),
(2, 'Ecouter les mouches'),
(3, 'Jouer au bilboquet'),
(4, 'Dormir pendant le cours'),
(5, 'Chercher un stage à Haïti'),
(6, 'Attendre les vacances'),
(7, 'Prendre le goûter'),
(8, 'Caresser le chat'),
(9, 'Ecouter de la musique'),
(10, 'Rien faire'),
(11, 'Jouer à Angular'),
(12, 'Rêver'),
(13, 'Travailler jour et nuit');

-- --------------------------------------------------------

--
-- Structure de la table `APPRENANT`
--

CREATE TABLE `APPRENANT` (
  `id` int(11) NOT NULL,
  `prenom` varchar(50) COLLATE utf8_roman_ci NOT NULL,
  `nom` varchar(50) COLLATE utf8_roman_ci NOT NULL,
  `dateDeNaissance` date NOT NULL,
  `mail` varchar(50) COLLATE utf8_roman_ci NOT NULL,
  `photo` varchar(50) COLLATE utf8_roman_ci NOT NULL,
  `id_REGION` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_roman_ci;

--
-- Contenu de la table `APPRENANT`
--

INSERT INTO `APPRENANT` (`id`, `prenom`, `nom`, `dateDeNaissance`, `mail`, `photo`, `id_REGION`) VALUES
(1, 'Géraldine', 'Autrique', '1970-12-27', 'geraldine.autrique@laposte.fr', '', 3),
(2, 'Nicolas', 'Filine', '1986-08-07', 'nicolas.filine@laposte.fr', '', 1),
(3, 'Pierre', 'Gorce', '1976-01-05', 'pierrexgorce@gmail.com', '', 1),
(4, 'Samuel', 'Joblon', '1973-10-18', 'samuel.joblon@gmail.com', '', 1),
(5, 'Phoneprasong', 'Khamvongsa', '0000-00-00', 'pomlao@hotmail.com', '', 2),
(6, 'Vincent', 'Lebegue', '1986-08-13', 'vincent.lebegue@labanquepostale.fr', '', 1),
(7, 'Matthieu', 'Londeix', '1981-05-19', 'matthieu.londeix@laposte.fr', '', 2),
(8, 'Thomas', 'Longueville', '1972-04-26', 'thomas.longueville@laposte.fr', '', 2),
(9, 'Christine', 'Métivier', '1980-04-29', 'christine.pereira@laposte.fr', '', 1),
(10, 'Laurent', 'Picard', '1972-05-23', 'laurent2.picard@laposte.fr', '', 1),
(11, 'David', 'Pouline', '1982-07-07', 'david.pouline@facteo.fr', '', 3),
(12, 'Julien', 'Prod\'homme', '1990-08-31', 'prodhomme.julien@gmail.com', '', 1),
(13, 'Samuel', 'Sabot', '1980-04-10', 'samuel.sabot@facteo.fr', '', 3),
(14, 'Salvatore', 'Sancesario', '1975-05-10', 'salvatore.sancesario@facteo.fr', '', 1),
(15, 'David', 'Sylvestre', '1986-07-06', 'david.sylvestre@mfacteur.fr', '', 2),
(16, 'Cédric', 'Tressous', '1984-08-08', 'cedric.tressous@gmail.com', '', 2),
(17, 'Zébulon', 'Zébutuc', '1977-03-13', 'zebulonzeb@free.fr', '', 2);

-- --------------------------------------------------------

--
-- Structure de la table `LISTE_ACTIVITES`
--

CREATE TABLE `LISTE_ACTIVITES` (
  `id` int(11) NOT NULL,
  `id_APPRENANT` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_roman_ci;

-- --------------------------------------------------------

--
-- Structure de la table `REGION`
--

CREATE TABLE `REGION` (
  `id` int(11) NOT NULL,
  `nom` varchar(50) COLLATE utf8_roman_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_roman_ci;

--
-- Contenu de la table `REGION`
--

INSERT INTO `REGION` (`id`, `nom`) VALUES
(1, 'ILE DE FRANCE'),
(2, 'PAYS DE LOIRE'),
(3, 'AQUITAINE');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `ACTIVITE`
--
ALTER TABLE `ACTIVITE`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `APPRENANT`
--
ALTER TABLE `APPRENANT`
  ADD PRIMARY KEY (`id`),
  ADD KEY `APPRENANT_REGION_FK` (`id_REGION`),
  ADD KEY `dateDeNaissance` (`dateDeNaissance`,`id_REGION`);

--
-- Index pour la table `LISTE_ACTIVITES`
--
ALTER TABLE `LISTE_ACTIVITES`
  ADD PRIMARY KEY (`id`,`id_APPRENANT`),
  ADD KEY `LISTE_ACTIVITES_APPRENANT0_FK` (`id_APPRENANT`);

--
-- Index pour la table `REGION`
--
ALTER TABLE `REGION`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `ACTIVITE`
--
ALTER TABLE `ACTIVITE`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT pour la table `APPRENANT`
--
ALTER TABLE `APPRENANT`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;
--
-- AUTO_INCREMENT pour la table `REGION`
--
ALTER TABLE `REGION`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `APPRENANT`
--
ALTER TABLE `APPRENANT`
  ADD CONSTRAINT `APPRENANT_REGION_FK` FOREIGN KEY (`id_REGION`) REFERENCES `REGION` (`id`);

--
-- Contraintes pour la table `LISTE_ACTIVITES`
--
ALTER TABLE `LISTE_ACTIVITES`
  ADD CONSTRAINT `LISTE_ACTIVITES_ACTIVITE_FK` FOREIGN KEY (`id`) REFERENCES `ACTIVITE` (`id`),
  ADD CONSTRAINT `LISTE_ACTIVITES_APPRENANT0_FK` FOREIGN KEY (`id_APPRENANT`) REFERENCES `APPRENANT` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
