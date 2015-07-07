;; This is our namespace, "my-sketch.core".
;; We can organize things in files, so we don't have huuuge files.
(ns harmful-fun.core
  ;; Pull in stuff from a couple Quil namespace. Quil is what lets us draw.
  ;; For example, when you see "q/background" below, that's the function
  ;; "background" from the "quil.core" namespace.
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(def screen-size 500)


(defn setup []
  ;; This is the initial state.
  {:x 100           ;; The ball's left/right position
   :y 150           ;; The ball's up/down position
   :change-in-x 1}) ;; Go right this many pixels in each frame rate.


(defn update-state [state]
  ;; Print the current state.
  ;; (In Light Table's menubar, select View, then Console to see it.)
  (println state)

  ;; Do different things depending on how far right we are.
  (if (> (:x state) 485)
    ;; Our ball just went past 485 pixels to the right.
    (assoc state
           ;; As usual, add :change-in-x units to the right.
           :x (+ (:x state)
                 (:change-in-x state))
           ;; Make :change-in-x negative, so the ball goes left from now on.
           :change-in-x -1)
    
    (assoc state
           ;; As usual, add :change-in-x units to the right.
           :x (+ (:x state)
                 (:change-in-x state))
           ;; :change-in-x stays in the same. (We can in fact delete this line.)
           :change-in-x (:change-in-x state))))

(defn draw-state [state]  
  (q/background 100)  ;; Set the canvas's background color.
  (q/fill (q/color 100 250 200))  ;; Fill the next shape you draw with this color.
  (q/ellipse (:x state) (:y state) 60 80))  ;; Draw ellipse with x/y/width/height.


(q/defsketch my-sketch
  ;; Setup everything at first. Like screen size, initial state, etc.
  :size [screen-size screen-size]
  :setup setup

  ;; These functions are called on each frame. Many times a second.
  ;; Update state, then draw it. Over and over again.
  :update update-state
  :draw draw-state

  ;; More setup.
  ;;
  ;; The canvas should be on top of every other window.
  :features [:keep-on-top]

  ;; Make state flow through our functions. Each function gets it as
  ;; input... then returns it as output.
  :middleware [m/fun-mode])
