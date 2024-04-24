(ns a-simple-http-api.routes
  (:require [a-simple-http-api.http :as http]))

(def routes
  [["/jobs" {:get  http/get-jobs
             :post http/insert-job!}]
   ["/jobs/:id" {:delete http/delete-job!}]])