--Product sale total per day, so we can extends the search if we group by day, we can do weeks, months, etc ...
CREATE OR REPLACE VIEW v_product_sale_per_day AS
    SELECT 
        p.name,
        COALESCE(SUM(sl.unit_price * sl.quantity)) as total_sale, 
        TO_DATE(TO_CHAR(s.date, 'YYYY-MM-DD'), 'YYYY-MM-DD') as date
    FROM
        sale s JOIN sale_detail sl ON sl.sale_id=s.id JOIN product p ON p.id=sl.product_id
    GROUP BY p.name, date;


-- SELECT SUM(total_sale) AS 
--     amount, TO_CHAR(date, 'YYYY-MM') as year_month
--     FROM v_product_sale_per_day 
--     GROUP BY 
--         TO_CHAR(date, 'YYYY-MM') HAVING TO_CHAR(date, 'YYYY-MM')=TO_CHAR(NOW(), 'YYYY-MM');

SELECT SUM(total_sale) AS 
    amount
    FROM v_product_sale_per_day 
    WHERE TO_CHAR(date, 'YYYY-MM')=TO_CHAR(NOW(), 'YYYY-MM');


-- SELECT 
--     name,
--     TO_CHAR(NOW(), 'YYYY-MM') as year_month,
--     SUM(total_sale) as total_sale_month
-- FROM 
--     v_product_sale_per_day
-- GROUP BY
--     name, year_month
-- ORDER BY total_sale_month DESC LIMIT 1;


SELECT 
    name
FROM 
    v_product_sale_per_day
WHERE TO_CHAR(date, 'YYYY-MM')=TO_CHAR(NOW(), 'YYYY-MM')
GROUP BY
    name
ORDER BY SUM(total_sale) DESC LIMIT 1;