--Total product sale per day
SELECT 
    p.name,
    COALESCE(SUM(sl.unit_price * sl.quantity)) as total_sale, 
    TO_DATE(TO_CHAR(s.date, 'YYYY-MM-DD'), 'YYYY-MM-DD') as date
FROM
    sale s JOIN sale_detail sl ON sl.sale_id=s.id JOIN product p ON p.id=sl.product_id
GROUP BY p.name, date;

--Total sale per month
SELECT 
    TO_DATE(TO_CHAR(s.date, 'YYYY-MM'), 'YYYY-MM') as year_month,
    SUM(s.total) as total_sale
FROM
    sale s
GROUP BY year_month;

--Top 3 most sold product per month
SELECT
    name,
    SUM(total_sale) as total_sale_per_month,
    TO_DATE(TO_CHAR(date, 'YYYY-MM'), 'YYYY-MM') as year_month
FROM 
    v_product_sale_per_day
GROUP BY name, year_month
ORDER BY total_sale_per_month DESC LIMIT 3;