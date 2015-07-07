;; This is our namespace, "harmful-fun.core".
;; We can organize things in files, so we don't have huuuge files.
(ns harmful-fun.core

  ;; Pull in stuff from a couple Quil namespace. Quil is what lets us draw.
  ;; For example, when you see "q/background" below, that's the function
  ;; "background" from the "quil.core" namespace.
  (:require [quil.core :as q]
            [quil.middleware :as m]))


(def screen-size 500)


(defn setup []
  ;; Fancy stuff someone put here to show possibilities.
  (q/frame-rate 100)
  (q/color-mode :rgb)

  ;; This is the initial state.
  {:x 100           ;; The ball's left/right position
   :y 150           ;; The ball's up/down position
   :change-in-x 1}) ;; Move right this many pixels in each frame rate.


(defn update-state [state]
  ;; Print the current state.
  ;; (In Light Table's menubar, select View, then Console to see it.)
  (println state)

  ;; Do different things depending on how far right we are.
  (if (> (:x state) 485)
    ;; This only happens if our ball just went too far right.
    (assoc state
           ;; As usual, add :change-in-x units to the right.
           :x (+ (:x state)
                 (:change-in-x state))
           ;; Move left. (Make :change-in-x negative.)
           :change-in-x -1)

    ;; The usual situation. The ball's moving in some direction.
    (assoc state
           ;; As usual, add :change-in-x units to the right.
           :x (+ (:x state)
                 (:change-in-x state))
           ;; :change-in-x stays in the same. (We can in fact delete this line.)
           :change-in-x (:change-in-x state))))


(defn draw-state [state]  
  (q/background 100)                       ;; Set the canvas's background color.
  (q/fill (q/color 100 250 200))           ;; Fill the next shape drawn w/ this color.
  (q/ellipse (:x state) (:y state) 60 80)) ;; Draw ellipse with x/y/width/height.


(q/defsketch my-sketch
  ;; Setup screen size.
  :size [screen-size screen-size]

  ;; defsketch needs a :setup function, an :update function and a
  ;; :draw function from us.
  :setup setup          ;; Call our setup function only once, at the start.
  :update update-state  ;; Call our update-state function many times a second.
  :draw draw-state      ;; Call our draw-state right after update-state.

  ;; More setup.
  ;;
  ;; The canvas should be on top of every other window.
  :features [:keep-on-top]

  ;; Make state flow through our functions:
  ;; - setup function returns an initial state object
  ;; - update-state gets a state object as input, then returns one
  ;; - draw-state gets a state object as input, then returns one
  :middleware [m/fun-mode])
