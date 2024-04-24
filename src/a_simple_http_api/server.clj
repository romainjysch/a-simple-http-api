(ns a-simple-http-api.server
  (:require [a-simple-http-api.routes :as routes]
            [reitit.ring              :as reitit]
            [ring.adapter.jetty       :as jetty]
            [ring.util.http-response  :as response]
            [ring.middleware.json     :refer [wrap-json-body]]))

(def handler
  (reitit/ring-handler
    (reitit/router routes/routes)
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