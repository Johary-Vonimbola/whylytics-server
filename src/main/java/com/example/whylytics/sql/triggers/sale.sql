CREATE OR REPLACE FUNCTION update_sale_total_price_fn()
RETURNS TRIGGER AS $$
BEGIN
    IF (TG_OP = 'DELETE') THEN
        UPDATE sale SET total=(
            SELECT 
                COALESCE(SUM(quantity * unit_price), 0) 
            FROM sale_detail 
            WHERE sale_id=OLD.sale_id
        ) WHERE id=OLD.sale_id;
    ELSE
        UPDATE sale SET total=(
            SELECT 
                COALESCE(SUM(quantity * unit_price), 0) 
            FROM sale_detail 
            WHERE sale_id=NEW.sale_id
        ) WHERE id=NEW.sale_id;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER update_sale_total_price 
AFTER INSERT OR UPDATE OR DELETE ON sale_detail
FOR EACH ROW EXECUTE PROCEDURE update_sale_total_price_fn();
