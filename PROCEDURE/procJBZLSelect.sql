DELIMITER $$

USE `phrv2`$$

DROP PROCEDURE IF EXISTS `procJBZLSelect`$$

CREATE DEFINER = `root` @`localhost` PROCEDURE `procJBZLSelect` (IN pageNo INT, IN pageSize INT) 
BEGIN
  IF(pageNo < 1) 
  THEN SET @start = 0 ;
  ELSE SET @start = (pageNo - 1) * pageSize ;
  END IF ;
  SET @end = pageSize ;
  SET @sql = 'SELECT * FROM phr_grda_jbzl order by grda_jdrq, grda_xm limit ?,?' ;
  PREPARE stmt FROM @sql ;
  EXECUTE stmt USING @start,
  @end ;
  DEALLOCATE PREPARE stmt ;
END $$

DELIMITER ;

