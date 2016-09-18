DELIMITER $$

USE `phrv2`$$

DROP PROCEDURE IF EXISTS `procJBZLInsert`$$

CREATE DEFINER = `root` @`localhost` PROCEDURE `procJBZLInsert` (IN json VARCHAR (500)) 
BEGIN
  SELECT 
    fnParseJson (json, 'operate') INTO @operate ;
  IF(@operate = 'insert') 
  
#SET @data = '{"data":{"grbh":"4401040190000001","grda_xm":"白逸阳","grda_xb":"男","grda_csrq":"2010-12-30"},"operate":"insert"}';
#CALL procJBZLInsert(@data)

#SELECT * FROM phr_grda_jbzl j WHERE j.grbh LIKE '11111%'  
  
  THEN 
  SELECT 
    INSERT INTO phr_grda_jbzl
  END IF ;
END $$

DELIMITER ;

