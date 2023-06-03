INSERT INTO chapter(name)
VALUES ('Английский:цвета');
INSERT INTO chapter(name)
VALUES ('Звуки животных');
INSERT INTO chapter(name)
VALUES ('Англисйкий:числа');

INSERT INTO card(question, answer, is_remembered, chapter_id)
VALUES ('BLUE', 'СИНИЙ', TRUE, 1);
INSERT INTO card(question, answer, is_remembered, chapter_id)
VALUES ('RED','КРАСНЫЙ', TRUE, 1);
INSERT INTO card(question, answer, is_remembered, chapter_id) 
VALUES ('BLACK','ЧЁРНЫЙ', TRUE, 1);

INSERT INTO card(question, answer, is_remembered, chapter_id)
VALUES ('МУУУУ', 'КОРОВА', TRUE, 2);
INSERT INTO card(question, answer, is_remembered, chapter_id) 
VALUES ('ГАВ', 'СОБАКА', TRUE, 2);
INSERT INTO card(question, answer, is_remembered, chapter_id) 
VALUES ('МЯУ', 'КОТ', TRUE, 2);

INSERT INTO card(question, answer, is_remembered, chapter_id)
VALUES ('TEN', 'ДЕСЯТЬ',FALSE,3);
INSERT INTO card(question, answer, is_remembered, chapter_id)
VALUES ('FIVE','ПЯТЬ',FALSE,3);
INSERT INTO card(question, answer, is_remembered, chapter_id)
VALUES ('THREE', 'ТРИ', FALSE, 3);


UPDATE card
SET is_remembered=TRUE
WHERE chapter_id=3;





