DELIMITER $$

USE `phrv2`$$

DROP PROCEDURE IF EXISTS `procUpdateData`$$

CREATE PROCEDURE `procUpdateData` (IN json VARCHAR (1000)) 
BEGIN
   /*
    SET @json = '{"dataTable":"phr_grda_jbzl","identification":{"grbh":1114,"grda_csrq":"2011-12-30"},"data":{"grbh":"1114","grda_xm":"胡","grda_csrq":"2011-12-30", "tjbj": 3}}';
    CALL procUpdateData(@json);
   */
   
   DECLARE i INT;
   DECLARE j INT;
   DECLARE keyStrUnQuote VARCHAR(600);
   DECLARE idsKeyStrUnQuote VARCHAR(400);
   DECLARE conditionStr VARCHAR(800);
   
   SET @dataTable = JSON_UNQUOTE(JSON_EXTRACT(json, '$.dataTable'));
   SET @data = JSON_EXTRACT(json, '$.data');
   SET @identification = JSON_EXTRACT(json, '$.identification');
   
   SET @keysArr = JSON_KEYS(@data);
   SET @keysArrLength = JSON_LENGTH(@data);
   
   SET @idsKeyArr = JSON_KEYS(@identification);
   SET @idsKeyArrLength = JSON_LENGTH(@identification);
   
	SET i=0;
	set conditionStr = '';
	WHILE i<@keysArrLength DO
	     SET @variable= CONCAT('$[',i,']');
	     
		SET @sql = 'set @temp_key = JSON_UNQUOTE(JSON_EXTRACT(?,?))';
		PREPARE stmt FROM @sql;
		EXECUTE stmt USING @keysArr, @variable;
		
		SET @sql = 'set @temp_value = JSON_EXTRACT(?,CONCAT("$.",JSON_UNQUOTE(JSON_EXTRACT(?, ?))))';
		PREPARE stmt FROM @sql ;
		EXECUTE stmt USING @data, @keysArr, @variable;
		
		IF (i = @keysArrLength - 1) THEN
			SET conditionStr = CONCAT(conditionStr, @temp_key, '=', @temp_value);
		ELSE
			SET conditionStr = CONCAT(conditionStr, @temp_key, '=', @temp_value, ',');
		END IF;
		SET i=i+1;
	END WHILE;
	
	SET j=0;
	SET idsKeyStrUnQuote = '';
	WHILE j<@idsKeyArrLength DO
		SET @variable= CONCAT('$[',j,']');
		
		SET @sql = 'set @temp_idsKey = JSON_UNQUOTE(JSON_EXTRACT(?,?))';
		PREPARE stmt FROM @sql;
		EXECUTE stmt USING @idsKeyArr, @variable;
		
		SET @sql = 'set @temp_idsValue = JSON_EXTRACT(?,CONCAT("$.",JSON_UNQUOTE(JSON_EXTRACT(?, ?))))';
		PREPARE stmt FROM @sql;
		EXECUTE stmt USING @identification, @idsKeyArr, @variable;
		
		SET idsKeyStrUnQuote = CONCAT(idsKeyStrUnQuote,' and ', @temp_idskey, '=', @temp_idsValue);
	
		SET j=j+1;
	END WHILE;
	
	SET @sql = CONCAT('update ',@dataTable,' set ',conditionStr,' where 1=1',idsKeyStrUnQuote);
	SELECT @sql;
	PREPARE stmt FROM @sql;
	EXECUTE stmt;
END $$

DELIMITER ;

