CREATE TABLE IF NOT EXISTS app.vote_artist
(
    id_vote bigint NOT NULL,
    id_artist bigint NOT NULL,
    CONSTRAINT vote_artist_id_vote_key UNIQUE (id_vote),
    CONSTRAINT vote_artist_id_artist_fkey FOREIGN KEY (id_artist)
        REFERENCES app.artists (id) MATCH SIMPLE
        ON UPDATE RESTRICT
        ON DELETE RESTRICT,
    CONSTRAINT vote_artist_id_vote_fkey FOREIGN KEY (id_vote)
        REFERENCES app.votes (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
