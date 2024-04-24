(ns a-simple-http-api.app
  (:require [a-simple-http-api.server :as http]
            [a-simple-http-api.board  :as board]
            [clojure.tools.logging    :as log]))

(defn main
  [& _]
  (log/info "Jobs board creation...")
  (board/populate-jobs)
  (log/info "Jobs board created.")
  (log/info "HTTP server starting...")
  (http/start-server)
  (log/info "HTTP server started."))