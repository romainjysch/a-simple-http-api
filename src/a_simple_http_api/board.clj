(ns a-simple-http-api.board)

(def jobs (atom {}))

(defn populate-jobs
  []
  (swap! jobs assoc (random-uuid) {:company "Exoscale" :title "Sales Specialist" :location "Vienna"})
  (swap! jobs assoc (random-uuid) {:company "Exoscale" :title "Site Reliability Engineer" :location "Lausanne"})
  (swap! jobs assoc (random-uuid) {:company "HEP-VD" :title "Network Engineer" :location "Lausanne"})
  (swap! jobs assoc (random-uuid) {:company "HEP-VD" :title "Project Manager" :location "Lausanne"}))