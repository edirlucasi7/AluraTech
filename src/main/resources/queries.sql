-- os nomes e ordem das subcategorias ativas e que tem algum curso, na ordem
SELECT DISTINCT s.name, s.order_visualization
FROM subcategory s
    INNER JOIN course c ON s.id = c.subcategory_id
WHERE active ORDER BY s.order_visualization;

-- o nome e a quantidade de cursos do instrutor que tem mais cursos
SELECT i.name, COUNT(*) AS quantidade_de_cursos
FROM instructor i
    INNER JOIN course c
ON i.id = c.instructor_id
GROUP BY name ORDER BY quantidade_de_cursos DESC LIMIT 1;

-- os nomes de todas as categorias ativas com a respectiva quantidade de cursos (públicos e privados) e total de horas
-- estimados dos cursos associados (sendo 0 se não existir nenhum curso)
SELECT ca.name, COALESCE(COUNT(co.id), 0) AS quantidade_cursos, COALESCE(SUM(co.estimated_time_in_hours), 0) AS total_horas_cursos
FROM category ca
	LEFT JOIN subcategory s ON ca.id = s.category_id
	LEFT JOIN course co ON s.id = co.subcategory_id
WHERE ca.active = true
GROUP BY ca.name;