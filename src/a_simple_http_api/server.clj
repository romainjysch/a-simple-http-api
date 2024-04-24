(ns a-simple-http-api.server
  (:require [a-simple-http-api.board :as board]
            [clojure.data.json :as json]
            [clojure.tools.logging :as log]
            [reitit.ring :as reitit]
            [ring.adapter.jetty :as jetty]
            [ring.util.http-response :as response]
            [ring.middleware.json :refer [wrap-json-body]]))

(defn jobs-handler
  [req]
  (log/info "Open positions showed.")
  {:status  200
   :headers {"Content-Type" "application/json"}
   :body    (str (json/write-str @board/jobs))})

(defn insert-job-handler
  [req]
  (let [body (:body req)
        company (get body "company")
        title (get body "title")
        location (get body "location")]
    (swap! board/jobs assoc (random-uuid) {:company company
                                           :title title
                                           :location location})
    (log/info (str "New job created. Company: " company ". Title: " title ". Location: " location "."))
    {:status  200
     :headers {"Content-Type" "application/json"}
     :body    (json/write-str @board/jobs)}))

(defn delete-job-handler
  [req]
  (let [id (get-in req [:path-params :id])]
    (swap! board/jobs dissoc (parse-uuid id))
    (log/info (str "Job " id " deleted."))
    {:status  200
     :headers {"Content-Type" "application/json"}
     :body    (str (json/write-str @board/jobs))}))

(def routes
  [["/jobs" {:get jobs-handler
             :post insert-job-handler}]
   ["/jobs/:id" {:delete delete-job-handler}]])

(def handler
  (reitit/ring-handler
    (reitit/router routes)
    (reitit/create-default-handler
      {:not-found
       (constantly (response/not-found "404 - Page not found"))
       :method-not-allowed
       (constantly (response/method-not-allowed "405 - Not allowed"))
       :not-acceptable
       (constantly (response/not-acceptable "406 - Not acceptable"))})))

(defn start-server
  []
  (jetty/run-jetty (-> #'handler wrap-json-body) {:port 8080 :join? false}))