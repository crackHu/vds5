SELECT * FROM phr_grda_jbzl;

INSERT INTO phr_grda_jbzl VALUES ('{"grbh":"1114","grda_xm":"胡永刚","grda_csrq":"2010-12-30 01:01:01", "tjbj": "2"}');
INSERT INTO phr_grda_jbzl('{"grbh", "grda_xm", "tjbj"}') VALUES ('{"grbh":"1114","grda_xm":"胡永刚", "tjbj": "2"}');
INSERT INTO phr_grda_jbzl(grbh, grda_xm, tjbj) VALUES ('{"grbh":"1114","grda_xm":"胡永刚", "tjbj": "2"}');
INSERT INTO phr_grda_jbzl(grbh, grda_xm, grda_csrq, tjbj) VALUES ('1114', '胡永刚', "2010-12-30 01:01:01", 2);


/*********2016年9月20日15:12:01*/
SELECT JSON_EXTRACT('{"grbh":"1114","grda_xm":"胡永刚", "tjbj": 2}', '$.tjbj');

SELECT * FROM phr_grda_jbzl j WHERE j.`grbh` = '1114';
INSERT INTO phr_grda_jbzl(grbh, grda_xm, grda_csrq, tjbj) VALUES ('1114', '胡永刚', "2010-12-30 01:01:01", 2);
UPDATE phr_grda_jbzl SET grda_xm = '1115', WHERE grbh = '1114'

SET @json = '{"dataTable":"phr_grda_jbzl","data":{"grbh":"1114","grda_xm":"胡永刚","grda_csrq":"2016-12-30", "tjbj": 2}}';
CALL procInsertData(@json);
SET @json = '{"dataTable":"phr_grda_jbzl","data":{"grbh":"1114","grda_xm":"胡永111","grda_csrq":"2016-12-30", "tjbj": 2},"identification":{"grbh","1114"}}';
CALL procUpdateData(@json);

INSERT INTO phr_grda_jbzl (grbh, tjbj, grda_xm, grda_csrq) VALUES ("1114",2,"胡永刚","2016-12-30 11:11:11")


SET @json1 = '{"grbh":"1114","grda_xm":"胡永刚","grda_csrq":"2016-12-30", "tjbj": 2}';
SELECT JSON_EXTRACT(JSON_KEYS(@json1),'$[0]');
SELECT JSON_QUOTE();
SELECT JSON_EXTRACT(@json1, "$.tjbj");
SET @i = CONCAT('$[',1,']');
SET @keysArr = JSON_KEYS(@json1);
SELECT @keysArr := JSON_KEYS(@json1);

SET @sql = 'SELECT JSON_EXTRACT(?,CONCAT("$.",JSON_UNQUOTE(JSON_EXTRACT(?, ?))))';
PREPARE stmt FROM @sql ;
EXECUTE stmt USING @json1, @keysArr, @i;

SELECT @test;



SELECT * FROM phr_grda_jbzl j WHERE j.`grbh` = '1114';

SELECT STR_TO_DATE('2012-10-11 11:11:11', '%Y-%m-%d %H:%i:%s');
SELECT STR_TO_DATE('2008-4-2 15:3:28','%Y-%m-%d %H:%i:%s');
/*********2016年9月20日15:12:01*/


SELECT JSON_UNQUOTE(JSON_EXTRACT('{"id": 1, "name": "mysql"}','$.name'));
SELECT JSON_UNQUOTE('{"id": 1, "name": "mysql"}'), JSON_UNQUOTE('"mysql"');

SELECT JSON_UNQUOTE('{"grbh":"1114","grda_xm":"胡永刚","grda_csrq":"2010-12-30 01:01:01", "tjbj": "2"}'), JSON_QUOTE('"null"');
/*Array*/
SELECT JSON_ARRAY (1, "abc", NULL, TRUE, CURTIME());
/*Object*/
SELECT JSON_OBJECT('id', 87, 'name', 'carrot');
/*Append*/
SET @j = '["a", {"b": [1, 2]}, [3, 4]]';
SELECT JSON_ARRAY_APPEND(@j, '$[1]', 1);
/*Merge*/
SELECT JSON_MERGE('[1, 2]', '[true, false]');
/*Remove*/
SET @j = '["a", ["b", "c"], "d"]';
SELECT JSON_REMOVE(@j, '$[1]');
/*Replace*/
SET @j = '{ "a": 1, "b": [2, 3]}';
SELECT JSON_REPLACE(@j, '$.a', 10, '$.c', '[true, false]');
/*Contain*/
SET @j = '{"a": 1, "b": 2, "c": {"d": 4}}';
SET @j2 = '1';
SELECT JSON_CONTAINS(@j, @j2, '$.b');

SET @j = '["a","b"]';
SELECT JSON_CONTAINS(@j, '["a"]');
/*Contain Path*/
SET @j = '{"a": 1, "b": 2, "c": {"d": 4}}';
SELECT JSON_CONTAINS_PATH(@j, 'one', '$.a', '$.e');
SELECT JSON_CONTAINS_PATH(@j, 'all', '$.a', '$.e');
/*Extract*/
SELECT JSON_EXTRACT('[10, 20, [30, 40]]', '$[1]');
SELECT JSON_EXTRACT('[10, 20, [30, 40]]', '$[1]', '$[0]');
SELECT JSON_EXTRACT('{"a": 1, "b": 2, "c": {"d": 4}}', '$.c');
SELECT JSON_EXTRACT('{"a": 1, "b": 2, "c": {"d": 4}}', '$.c', '$.a');

/*Keys*/
SELECT JSON_KEYS('{"a": 1, "b": {"c": 30}}');
/*Search*/
SET @j = '["abc", [{"k": "10"}, "def"], {"x":"abc"}, {"y":"bcd"}]';
SELECT JSON_SEARCH(@j, 'one', 'abc');
SELECT JSON_SEARCH(@j, 'all', 'abc');
SET @j = '{"a": "1", "b": 2, "c": {"d": "1"}}';
SELECT JSON_SEARCH(@j, 'one', '1');
SELECT JSON_SEARCH(@j, 'all', '1');
/*Depth*/
SELECT JSON_DEPTH('{"a": "1", "b": 2, "c": {"d": "1"}}');
/*Length*/
SELECT JSON_LENGTH('{"a": 1, "b": {"c": 30}}');
/*Type*/
SET @j = '{"a": [10, true]}';
SELECT JSON_TYPE(@j);
SELECT JSON_TYPE(JSON_EXTRACT(@j, '$.a'));
/*Valid*/
SELECT JSON_VALID('{"a": 1}');
SELECT JSON_VALID('[10, 20, [30, 40]]');
SELECT JSON_VALID('hello'), JSON_VALID('"hello"');
/*Length*/
SELECT JSON_LENGTH('{"a": 1, "b": {"c": 30}}');


CREATE TABLE `article` (
  `id` MEDIUMINT (8) UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR (200) NOT NULL,
  `tags` json DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE = INNODB ;

INSERT INTO `article` (`title`, `tags`) 
VALUES
  (
    '体验 Mysql JSON',
    '["Mysql", "Database"]'
  ) ;


SELECT * FROM `article`
WHERE JSON_CONTAINS(tags, '["Mysql"]');
