SELECT DISTINCT
	excercise1.city,
	(
		SELECT excercise2.client
		FROM excercise AS excercise2
		WHERE excercise1.city = excercise2.city
		GROUP BY 1
		ORDER BY SUM(excercise2.amount) DESC, excercise2.client ASC
		LIMIT 1
	)
FROM excercise AS excercise1
ORDER BY excercise1.city ASC