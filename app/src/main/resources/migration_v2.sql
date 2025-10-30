ALTER TABLE band
ADD COLUMN comments VARCHAR(1024) NULL;

ALTER TABLE song
ADD COLUMN you_tube_clip_code VARCHAR(1024) NULL;

ALTER TABLE band
ADD COLUMN pinned INTEGER NULL DEFAULT 0;

CREATE TABLE genre (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    description VARCHAR(50) NOT NULL
);

INSERT INTO genre (description) VALUES
('Rock'),
('Disco'),
('Indie Rock'),
('Hard Rock'),
('Metal'),
('Rap'),
('Hiphop'),
('Lounge'),
('Ballad'),
('Love song');

