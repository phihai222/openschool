import {useStore} from "zustand/react";

export function BearCounter() {
  const bears = useStore((state) => state.bears)
  return <h1>{bears} bears around here...</h1>
}

export function Controls() {
  const increasePopulation = useStore((state) => state.increasePopulation)
  return <button onClick={increasePopulation}>one up</button>
}
