DELIMITER $$

USE `phrv2`$$

DROP FUNCTION IF EXISTS `fnQueryJson` $$

CREATE DEFINER = `root` @`%` FUNCTION `fnQueryJson` (
  p_jsonstr VARCHAR (255) CHARACTER SET utf8,
  p_key VARCHAR (255)
) RETURNS VARCHAR (255) CHARSET utf8mb4 
BEGIN
  -- json取值
  DECLARE rtnVal VARCHAR (255) DEFAULT '' ;
  DECLARE v_key VARCHAR (255) ;
  SET v_key = CONCAT('"', p_key, '":') ;
  SET @v_flag = p_jsonstr REGEXP v_key ;
  IF(@v_flag = 0) 
  THEN SET rtnVal = '' ;
  ELSE 
  SELECT 
    val INTO rtnVal 
  FROM
    (SELECT 
      @start_pos := LOCATE(v_key, p_jsonstr),
      @end_pos := @start_pos + LENGTH(v_key),
      @end_ppos := IF(
        LOCATE("{", p_jsonstr, @end_pos) = @end_pos,
        @end_pos,
        @end_pos + 1
      ),
      @tail_pos := IF(
        LOCATE("{", p_jsonstr, @end_ppos) = @end_ppos,
        LOCATE("}", p_jsonstr, @end_ppos) + 2,
        IF(
          LOCATE(",", p_jsonstr, @end_ppos) = 0,
          LOCATE("}", p_jsonstr, @end_ppos),
          LOCATE(",", p_jsonstr, @end_ppos)
        )
      ),
      SUBSTRING(
        p_jsonstr,
        @end_ppos,
        @tail_pos - @end_ppos - 1
      ) AS val) AS t ;
  END IF ;
  RETURN rtnVal ;
END $$

DELIMITER ;

