INSERT INTO
    user(first_name, last_name, pesel)
VALUES
    ('Jan', 'Kowalski', '0123456789'),
    ('Paweł', 'Zawiał', '1234567890'),
    ('Marta', 'Babiak', '2345678901'),
    ('Karolina', 'Modejska', '3456789012'),
    ('Piotr', 'Pawelski', '4567890123');
INSERT INTO
    category( name, description )
VALUES
    ('Laptopy', 'Laptopy, notebooki itd'),
    ('Pojazdy', 'Samochody, samoloty, pociągi'),
    ('Telefony', 'Telefony komórkowe');
INSERT INTO
    asset( name, description, serial_number, category_id )
VALUES
    ('Asus MateBook D', '15 calowy laptop, i5, 8GB DDR3, kolor czarny', 'ASMBD198723', 1),
    ('Apple MacBook Pro 2015', '13 calowy laptop, i5, 16GB DDR3, SSD256GB, kolor srebrny', 'MBP15X0925336', 1),
    ('Audi A4 Avant', 'Audi Kombi, 1.9TDI, kolor szampan', 'VINDI3576XO526716', 2),
    ('Apple iPhone X', 'Telefon z zestawem słuchawkowym lightning i ładowarką', 'APLX17287GHX21', 3),
    ('Apple iPhone 8', 'Telefon z zestawem słuchawkowym lightning i ładowarką', 'APL8185652HGT7', 3);
