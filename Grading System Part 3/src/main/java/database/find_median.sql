SET
@rowindex := -1;
SELECT AVG(d.mark) as Median
FROM (SELECT @rowindex:=@rowindex + 1 AS rowindex, sm.mark AS mark
      FROM (SELECT m.mark FROM marks AS m WHERE m.course_id IN
          (SELECT c.course_id FROM courses c WHERE c.course_name = ? )) AS sm
      ORDER BY sm.mark) AS d
WHERE d.rowindex IN (FLOOR(@rowindex / 2), CEIL(@rowindex / 2));