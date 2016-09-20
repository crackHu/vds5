DELIMITER $$

USE `phrv2`$$

DROP FUNCTION IF EXISTS `fnParseJson` $$

CREATE DEFINER = `root` @`%` FUNCTION `fnParseJson` (
  p_jsonstr VARCHAR (255) CHARACTER SET utf8,
  p_type VARCHAR (255)
) RETURNS VARCHAR (255) CHARSET utf8mb4 
BEGIN
  -- json解析
  DECLARE rtnVal VARCHAR (255) DEFAULT '' ;
  
  IF(p_type = 'key') 
  THEN
	rtnVal = p_type;
  ELSEIF(p_type='value')
	rtnVal = p_type;
  END IF;
  RETURN rtnVal ;
END $$

DELIMITER ;

