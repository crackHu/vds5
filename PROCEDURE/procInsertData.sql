DELIMITER $$

USE `phrv2`$$

DROP PROCEDURE IF EXISTS `procInsertData`$$

CREATE PROCEDURE `procInsertData` (IN json VARCHAR (500)) 
BEGIN
   /*
     SET @json = '{"dataTable":"phr_grda_jbzl","data":{"grbh":"1114","grda_xm":"胡永刚","grda_csrq":"2016-12-30", "tjbj": 2}}';
     CALL test(@json)
   */
   DECLARE i INT;
   DECLARE keyStrUnQuote VARCHAR(1000);
   DECLARE valuesStr VARCHAR(1000);
   
   SET @dataTable = JSON_UNQUOTE(JSON_EXTRACT(json, '$.dataTable'));
   SET @data = JSON_EXTRACT(json, '$.data');
   
   SET @keysArr = JSON_KEYS(@data);
   SET @keysArrLength = JSON_LENGTH(@data);
   
	SET i=0;
	SET keyStrUnQuote = '';
	SET valuesStr = '';
	WHILE i<@keysArrLength DO
	     SET @variable= CONCAT('$[',i,']');
	     
		SET @sql = 'set @temp_key = JSON_UNQUOTE(JSON_EXTRACT(?,?))';
		PREPARE stmt FROM @sql;
		EXECUTE stmt USING @keysArr, @variable;
		
	     SET @sql = 'set @temp_value = JSON_EXTRACT(?,CONCAT("$.",JSON_UNQUOTE(JSON_EXTRACT(?, ?))))';
		PREPARE stmt FROM @sql ;
		EXECUTE stmt USING @data, @keysArr, @variable;
		
		IF (i = @keysArrLength - 1) THEN
			SET keyStrUnQuote = CONCAT(keyStrUnQuote,@temp_key);
			SET valuesStr = CONCAT(valuesStr,@temp_value);
		ELSE
			SET keyStrUnQuote = CONCAT(keyStrUnQuote,CONCAT(@temp_key,","));
			SET valuesStr = CONCAT(valuesStr,CONCAT(@temp_value,","));
		END IF;
		
		SET i=i+1;
	END WHILE;

	SET @sql = CONCAT('insert into ',@dataTable,' (',keyStrUnQuote,') values (',valuesStr,')');
	PREPARE stmt FROM @sql;
	EXECUTE stmt;
END $$

DELIMITER ;

