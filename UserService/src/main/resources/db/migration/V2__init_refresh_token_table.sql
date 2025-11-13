CREATE TABLE IF NOT EXISTS refresh_tokens (
                                              id UUID PRIMARY KEY,
                                              user_id UUID NOT NULL,
                                              token_hash TEXT NOT NULL,
                                              issued_at TIMESTAMP NOT NULL,
                                              expires_at TIMESTAMP NOT NULL,
                                              revoked BOOLEAN DEFAULT FALSE,
                                              CONSTRAINT fk_refresh_user FOREIGN KEY (user_id)
                                                  REFERENCES users(id)
                                                  ON DELETE CASCADE
);