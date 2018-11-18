(ns valeros.env
  (:require [selmer.parser :as parser]
            [clojure.tools.logging :as log]
            [valeros.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[valeros started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[valeros has shut down successfully]=-"))
   :middleware wrap-dev})
