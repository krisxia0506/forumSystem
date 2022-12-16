-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: forumdb
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

--
-- Table structure for table `collection`
--
DROP database IF EXISTS forumdb;
create database forumdb;
use forumdb;

DROP TABLE IF EXISTS `collection`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `collection`
(
    `id`      int NOT NULL AUTO_INCREMENT,
    `user_id` int NOT NULL,
    `post_id` int NOT NULL,
    `date`    varchar(50) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `collection_user_id_post_id_uindex` (`user_id`, `post_id`),
    KEY `collection_post_post_id_fk` (`post_id`),
    CONSTRAINT `collection_post_post_id_fk` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `collection_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 113
  DEFAULT CHARSET = utf8mb3 COMMENT ='收藏';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `collection`
--

LOCK TABLES `collection` WRITE;
/*!40000 ALTER TABLE `collection`
    DISABLE KEYS */;
INSERT INTO `collection`
VALUES (38, 1, 73, '2022-12-07 18:55:37'),
       (40, 17, 6, '2022-12-08 19:57:33'),
       (47, 17, 8, '2022-12-09 11:21:49'),
       (49, 1, 8, '2022-12-09 11:22:15'),
       (51, 1, 6, '2022-12-09 11:23:04'),
       (53, 17, 3, '2022-12-09 11:23:23'),
       (64, 17, 18, '2022-12-10 19:09:24'),
       (66, 1, 25, '2022-12-12 18:47:00'),
       (86, 8, 6, '2022-12-12 19:43:01'),
       (110, 1, 18, '2022-12-14 08:55:27'),
       (112, 21, 18, '2022-12-15 14:32:16');
/*!40000 ALTER TABLE `collection`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post`
(
    `post_id`      int NOT NULL AUTO_INCREMENT,
    `post_title`   varchar(100) DEFAULT NULL,
    `post_content` text,
    `post_author`  int          DEFAULT NULL,
    `post_time`    varchar(20)  DEFAULT NULL,
    `post_keyword` varchar(60)  DEFAULT NULL,
    `theme`        int          DEFAULT '0',
    `post_hits`    int          DEFAULT NULL COMMENT '点击量',
    PRIMARY KEY (`post_id`),
    UNIQUE KEY `id` (`post_id`),
    KEY `post_user_id_fk` (`post_author`),
    KEY `post_post_type_post_type_id_fk` (`theme`),
    CONSTRAINT `post_post_type_post_type_id_fk` FOREIGN KEY (`theme`) REFERENCES `theme` (`theme_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `post_user_id_fk` FOREIGN KEY (`post_author`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 56
  DEFAULT CHARSET = utf8mb3 COMMENT ='帖子';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post`
    DISABLE KEYS */;
INSERT INTO `post`
VALUES (1, 'openharmony 能否调用三方so库', '在openharmony 的DevEco 3上能不能直接调用jnative 的so库文件呢？谢谢', 1,
        '2022-12-08 09:32:53', 'openharmony', 13, 9),
       (2, 'Java中的常用异常处理方法 java推荐',
        '在Java中，异常情况分为Exception（异常）和Error（错误）两大类，Java异常通常是指程序运行过程中出现的非正常情况，如用户输入错误、除数为零、需要处理的文件不存在、数组下标越界等，对于异常情况的出现，可以采用异常处理，以保证程序可以正常的执行。\r\nJava中定义两种类型的异常和错误：\r\n1. JVM(Java虚拟机) 异常：由 JVM 抛出的异常或错误。例如：NullPointerException 类，ArrayIndexOutOfBoundsException 类，ClassCastException 类。\r\n2. 程序级异常：由程序或者API程序抛出的异常。例如 IllegalArgumentException 类，IllegalStateException 类。',
        1, '2022-12-08 09:47:37', '异常', 1, 6),
       (3, 'C语言初学者，编程易犯错误',
        'C编译的程序对语法检查并不像其它高级语言那么严格，这就给编程人员留下“灵活的余地”，但还是由于这个灵活给程序的调试带来了许多不便，尤其对初学C语言的人来说，经常会出一些连自己都不知道错在哪里的错误。看着有错的程序，不知该如何改起，通过对C的学习，积累了一些C编程时常犯的错误，以供参考。\r\n1、书写标识符时，忽略了大小写字母的区别 main() { 　　int a=5; 　　printf(“%d”,A); } 编译程序把a和A认为是两个不同的变量名，而显示出错信息。C认为大写字母和小写字母是两个不同的字符。习惯上，符号常量名用大写，变量名用小写表示，以增加可读性。\r\n\r\n2、忽略了变量的类型，进行了不合法的运算main() { 　　float a,b; 　　printf(“%d”,a%b); } %是求余运算，得到a/b的整余数。整型变量a和b可以进行求余运算，而实型变量则不允许进行“求余”运算。',
        1, '2022-12-08 09:48:52', '错误', 3, 11),
       (5, 'C#数据进制转换显示错误是怎么回事？',
        '现在遇到一个问题，下位机发送两个数据到上位机，上位机可以正确按字节接受到了。剩余的是将两个字节数据合并成一个整数，用于波形显示。具体如下\r\n单片机要发送的数据是：\r\n[C#] 纯文本查看 复制代码\r\nshort pitch=50,roll=280\r\n上位机按照十六进制接收数据如下：\r\n[C#] 纯文本查看 复制代码\r\n00 32 01 18\r\n这个结果是正确的。\r\n我将每两个字节数据拼接成一个C#里面的Int 32 类型的数据，但结果显示错误：\r\n[C#] 纯文本查看 复制代码\r\n00 32 01 18 //上位机接收到的十六进制数据69632 1024//数据拼接后显示的数据\r\n采用的拼接方法如下：\r\n[C#] 纯文本查看 复制代码\r\npitch = (UsefulData[0] << 8 + UsefulData[1]);//UsefulData是上位机解析数据帧之后存储的有效数据数组                              roll = (UsefulData[2] << 8 + UsefulData[3]);\r\n单片机发送数据是高低位是高字节在前，所以这个原因被排除了，那请问还可能是其他什么原因呢？ ',
        1, '2022-12-08 10:57:32', 'C#', 4, 1),
       (6, '嵌入式软件错误的原因有哪些',
        '在嵌入式开发软件中查找和消除潜在的错误是一项艰巨的任务。通常需要英勇的努力和昂贵的工具才能从观察到的崩溃，死机或其他计划外的运行时行为追溯到根本原因。在最坏的情况下，根本原因会破坏代码或数据，使系统看起来仍然可以正常工作或至少在一段时间内仍能正常工作。工程师常常放弃尝试发现不常见异常的原因，这些异常在实验室中不易再现，将其视为用户错误或“小故障”。然而，机器中的这些鬼魂仍然存在。这是难以重现错误的最常见根本原因指南。每当您阅读固件源代码时，请查找以下五个主要错误。并遵循建议的最佳做法，以防止它们再次发生 ',
        1, '2022-12-08 11:09:54', '错误', 5, 64),
       (7, 'linux错误码代码总结 ',
        '查看错误代码errno是调试程序的一个重要方法。当linuc Capi函数发生异常时,一般会将errno变量(需includeerrno.h)赋一个整数值,不同的值表示不同的含义,可以通过查看该值推测出错的原因。在实际编程中用这一招解决了不少原本看来莫名其妙的问题。比较麻烦的是每次都要去linux源代码里面查找错误代码的含义，现在把它贴出来，以后需要查时就来这里看了。 ',
        1, '2022-12-08 11:11:11', '错误', 6, 2),
       (8, '内嵌的Servlet配置如何修改',
        'SpringBoot默认采用嵌入式的Servlet容器（Tomcat）。那么内嵌的Servlet配置如何修改？可以使用配置文件或者yml文件来修改server:         port: 80        content-path: /crud        tomcat:                 uri-encoding: UTF-8#通用Servlet容器配置server.xxx#Tomcat配置server.tomcat.xxx编写WebServerFactoryCustomizer （自定义Web服务工厂）来修改Servl ',
        1, '2022-12-08 11:12:09', 'Servlet', 7, 41),
       (9, 'vue-cli-----vue实例中template:\'<App/>是什么意思？',
        '哪位大神知道vue-cli-----vue实例中template:\'是什么意思吗？', 17, '2022-12-08 11:23:00', 'vue', 8, 2),
       (10, '关于react中的常见错误及解决',
        '报错：type缺少props验证\r\n\r\n解决：\r\n\r\n1.查看下propTypes是否写成大写了，因为我们引入的时候是大写的，所以很多小伙伴可能直接复制过来就成大写了，也会报错哦\r\n\r\n2.新增type: PropTypes.number',
        17, '2022-12-08 11:24:02', 'react', 8, 5),
       (18, 'java替换字符串中的换行符',
        '前端录入的信息，有换行符\\r\\n，后面拿到数据库存储的数据后需要在前端页面上换行予以显示。<br />\r\nString testStr = &quot;换行\\r\\n换行&quot;;<br />\r\nString result = testStr.replaceAll(&quot;(\\\\r\\\\n|\\\\n|\\\\n\\\\r)&quot;,&quot;&quot;);<br />\r\n不用\\\\\\\\r\\\\\\\\n进行替换，如果字段之间包含\\r\\n则需要这样进行替换。<br />\r\n已经转义的则使用\\\\r\\\\n进行字符串替换就可以正常替换成<br />\r\n<br />\r\n&nbsp;',
        1, '2022-12-08 12:03:25', '换行', 1, 139),
       (19, '机器学习真能产生智能决策吗？',
        '历经三年时间，我们在2022年完成了图灵奖获得者、加州大学洛杉矶分校计算机科学教授，美国国家科学院院士，被誉为“贝叶斯网络之父”的朱迪亚·珀尔大作《因果论：模型、推理和推断》。<br/><br/>这本书原版的第1版写于2000年，开创了因果分析和推断的新思想和新方法，一出版就得到广泛的好评，促进了数据科学、人工智能、机器学习、因果分析等领域新的革命，在学术界产生了很大的影响。',
        8, '2022-12-08 12:54:41', '机器学习', 14, 1),
       (21, ' 关于equals方法导致的空指针异常如何解决 ',
        '一名大一学生，最近有一个简单学生信息管理系统的作业，在运行的时候有一个空指针异常，在网上搜寻了各种解释以及解决办法都没有能够解决，请各位大神帮忙指点一下。为了方便我把所涉及的类都放在了下面，但是出现异常的在第一个类。\r\nScoreData 类\r\n\r\n这个类的要求是通过键盘输入若干组学生课程成绩值，每组学生课程成绩值包含（学号，课程号，成绩）三个数据；要求注意学号和课程号和前面的数据保持一致，即，学号和课程号的值必须是 StudentData 和 CourseData 中有的数据，如在前两个类中没有，不要加入。\r\n\r\n————————————————\r\n原文作者：myyds\r\n转自链接：https://learnku.com/java/t/68319\r\n版权声明：著作权归作者所有。商业转载请联系作者获得授权，非商业转载请保留以上作者信息和原文链接。',
        1, '2022-12-08 20:11:36', '', 1, 2),
       (22, ' springboot @Configuration 注解详解 ',
        '场景\r\n\r\n新建两个 bean：user 和 pet。\r\n\r\n若要将这两个 bean 的实例注入到容器之中，在曾经 spring 阶段我们的做法是使用 xml 进行配置。\r\n\r\n在项目的 resource 文件夹下新建 bean.xml 并进行配置，配置内容如下：\r\n\r\n————————————————\r\n原文作者：kudoForever\r\n转自链接：https://learnku.com/articles/71782\r\n版权声明：著作权归作者所有。商业转载请联系作者获得授权，非商业转载请保留以上作者信息和原文链接。',
        1, '2022-12-08 20:11:41', '', 1, 1),
       (23, ' Java-Base64转图片并存储到本地(工具类) ', '解决富文本框中图片问题不能下载，想办法存储到本地来实现解决办法。',
        1, '2022-12-08 20:11:44', '', 1, 1),
       (24, ' 为什么有人不推荐使用spring官方推荐的@Transactional声明式注解 ',
        '事务管理在系统开发中是不可缺少的一部分，Spring 提供了很好事务管理机制，主要分为编程式事务和声明式事务两种。\r\n\r\n关于事务的基础知识，如什么是事务，数据库事务以及 Spring 事务的 ACID、隔离级别、传播机制、行为等，就不在这篇文章中详细介绍了。默认大家都有一定的了解。\r\n\r\n本文，作者会先简单介绍下什么是声明式事务和编程式事务，再说一下为什么我不建议使用声明式事务。\r\n编程式事务\r\n\r\n基于底层的 API，如 PlatformTransactionManager、TransactionDefinition 和 TransactionTemplate 等核心接口，开发者完全可以通过编程的方式来进行事务管理。\r\n\r\n编程式事务方式需要是开发者在代码中手动的管理事务的开启、提交、回滚等操作。\r\n\r\n————————————————\r\n原文作者：zhaozhangxiao\r\n转自链接：https://learnku.com/articles/69225\r\n版权声明：著作权归作者所有。商业转载请联系作者获得授权，非商业转载请保留以上作者信息和原文链接。',
        1, '2022-12-08 20:11:47', '', 1, 7),
       (25, ' java可以写一个解析终端设备的数据解析器吗，支持多种主流协议 ',
        '查看通讯报文等，请教一下有经验的大佬赐教一下大体方向 &quot;class=&quot;reference-link&quot;&gt; 有自己的电表，做一套能耗管理系统，有属于自己的云平台，前期的数据采集如何下手',
        1, '2022-12-08 20:11:50', '123', 1, 35),
       (55, '你好', '你好<br />\r\n<em>你好</em><br />\r\n<strong>你好</strong>', 21, '2022-12-16 09:02:46', '', 1, 1);
/*!40000 ALTER TABLE `post`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reply`
--

DROP TABLE IF EXISTS `reply`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reply`
(
    `reply_id`      int NOT NULL AUTO_INCREMENT,
    `reply_content` text,
    `reply_user`    int         DEFAULT NULL,
    `reply_time`    varchar(40) DEFAULT NULL,
    `post_id`       int         DEFAULT NULL,
    PRIMARY KEY (`reply_id`),
    UNIQUE KEY `id` (`reply_id`),
    KEY `reply_post_post_id_fk` (`post_id`),
    KEY `reply_user_id_fk` (`reply_user`),
    CONSTRAINT `reply_post_post_id_fk` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `reply_user_id_fk` FOREIGN KEY (`reply_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 31
  DEFAULT CHARSET = utf8mb3 COMMENT ='回帖';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reply`
--

LOCK TABLES `reply` WRITE;
/*!40000 ALTER TABLE `reply`
    DISABLE KEYS */;
INSERT INTO `reply`
VALUES (3, '谢谢，很有帮助，赞一个', 40, '2022-12-08 11:13:52', 3),
       (4, '嵌入式有什么好的书么', 40, '2022-12-08 11:14:08', 6),
       (5, '我也遇到了这个问题，已经解决了，很好', 17, '2022-12-08 11:14:45', 2),
       (6, '我是在学校学的，很全面，计算机组成原理也是基础', 17, '2022-12-08 11:16:03', 6),
       (7, '很有帮助', 8, '2022-12-08 12:04:48', 18),
       (16, '可以', 1, '2022-12-11 15:20:13', 25),
       (17, '著作权归作者所有。商业转载请联系作者获得授权，非商业转载请保留以上作者信息和原文链接。', 8,
        '2022-12-11 16:59:13', 24),
       (30, '谢谢', 21, '2022-12-16 09:02:59', 55);
/*!40000 ALTER TABLE `reply`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `theme`
--

DROP TABLE IF EXISTS `theme`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `theme`
(
    `theme_id`           int NOT NULL AUTO_INCREMENT,
    `theme_title`        varchar(20) DEFAULT NULL,
    `theme_introduction` text,
    PRIMARY KEY (`theme_id`),
    UNIQUE KEY `id` (`theme_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 15
  DEFAULT CHARSET = utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `theme`
--

LOCK TABLES `theme` WRITE;
/*!40000 ALTER TABLE `theme`
    DISABLE KEYS */;
INSERT INTO `theme`
VALUES (1, 'Java', 'Java是一门面向对象的编程语言'),
       (3, 'C/C++', 'C语言是一门面向过程的、抽象化的通用程序设计语言，广泛应用于底层开发。'),
       (4, 'C#', 'C#是微软公司发布的一种由C和C++衍生出来的面向对象的编程语言的高级程序设计语言。'),
       (5, '嵌入式', '嵌入式系统由硬件和软件组成．是能够独立进行运作的器件。'),
       (6, 'Linux', 'Linux'),
       (7, 'JavaWeb', 'JavaWeb'),
       (8, '前端', '前端1'),
       (9, '后端', '后端'),
       (13, 'HarmonyOS', 'HarmonyOS技术社区，高手带你玩转鸿蒙OS'),
       (14, 'Python', 'Python');
/*!40000 ALTER TABLE `theme`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user`
(
    `id`         int         NOT NULL AUTO_INCREMENT,
    `role`       int         NOT NULL,
    `username`   varchar(20) NOT NULL,
    `password`   varchar(20) NOT NULL,
    `nickname`   varchar(20)  DEFAULT NULL,
    `gender`     varchar(10)  DEFAULT NULL,
    `resume`     varchar(100) DEFAULT NULL,
    `level`      varchar(20)  DEFAULT '技术小白',
    `post_times` int          DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`),
    UNIQUE KEY `username` (`username`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 22
  DEFAULT CHARSET = utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user`
    DISABLE KEYS */;
INSERT INTO `user`
VALUES (1, 99, 'admin', 'admin', '管理员', 'male', 'wobushiadmin', '技术宗师', 42),
       (8, 1, '无敌霹雳风火轮', '123123', '霹雳火', 'felmale', '', '技术宗师', 31),
       (17, 1, '202007024220夏佳怡', '123', '夏佳怡', 'male', '20200702422', '技术大咖', 11),
       (21, 1, 'admin1', '123123', '123', 'male', '', '技术小白', 3),
       (40, 99, 'xiajiayi', '1', '夏佳怡3', 'male', 'assdasd', '技术小咖', 5);
/*!40000 ALTER TABLE `user`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'forumdb'
--

--
-- Dumping routines for database 'forumdb'
--
/*!50003 DROP PROCEDURE IF EXISTS `level_procedure` */;
/*!50003 SET @saved_cs_client = @@character_set_client */;
/*!50003 SET @saved_cs_results = @@character_set_results */;
/*!50003 SET @saved_col_connection = @@collation_connection */;
/*!50003 SET character_set_client = utf8mb4 */;
/*!50003 SET character_set_results = utf8mb4 */;
/*!50003 SET collation_connection = utf8mb4_0900_ai_ci */;
/*!50003 SET @saved_sql_mode = @@sql_mode */;
/*!50003 SET sql_mode =
        'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */;
DELIMITER ;;
CREATE
    DEFINER = `root`@`localhost` PROCEDURE `level_procedure`()
begin
    update user
    set level =
            case
                when post_times < 5 then '技术小白'
                when post_times < 10 then '技术小咖'
                when post_times < 20 then '技术大咖'
                when post_times < 30 then '技术大神'
                else '技术宗师'
                end;
end ;;
DELIMITER ;
/*!50003 SET sql_mode = @saved_sql_mode */;
/*!50003 SET character_set_client = @saved_cs_client */;
/*!50003 SET character_set_results = @saved_cs_results */;
/*!50003 SET collation_connection = @saved_col_connection */;
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2022-12-16 11:02:56
