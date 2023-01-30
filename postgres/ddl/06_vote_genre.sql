CREATE TABLE IF NOT EXISTS app.vote_genre
(
    id_vote bigint,
    id_genre bigint,
    CONSTRAINT vote_genre_id_vote_id_genre_key UNIQUE (id_vote, id_genre),
    CONSTRAINT vote_genre_id_genre_fkey FOREIGN KEY (id_genre)
        REFERENCES app.genres (id) MATCH SIMPLE
        ON UPDATE RESTRICT
        ON DELETE RESTRICT,
    CONSTRAINT vote_genre_id_vote_fkey FOREIGN KEY (id_vote)
        REFERENCES app.votes (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)