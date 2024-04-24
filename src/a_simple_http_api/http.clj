(ns a-simple-http-api.http
  (:require [a-simple-http-api.board :as board]
            [clojure.data.json       :as json]
            [clojure.tools.logging   :as log]))

(defn get-jobs
  [req]
  (log/info "Open positions showed.")
  {:status  200
   :headers {"Content-Type" "application/json"}
   :body    (str (json/write-str @board/jobs))})

(defn insert-job!
  [req]
  (let [body (:body req)
        company (get body "company")
        title (get body "title")
        location (get body "location")]
    (board/insert-job! company title location)
    (log/info (str "New job created. Company: " company ". Title: " title ". Location: " location "."))
    {:status  201
     :headers {"Content-Type" "application/json"}
     :body    (json/write-str @board/jobs)}))

(defn delete-job!
  [req]
  (let [id (get-in req [:path-params :id])]
    (board/delete-job! id)
    (log/info (str "Job " id " deleted."))
    {:status  202
     :headers {"Content-Type" "application/json"}
     :body    (str (json/write-str @board/jobs))}))