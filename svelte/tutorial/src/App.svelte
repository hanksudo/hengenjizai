<script>
  import svelteLogo from './assets/svelte.svg'
  import Counter from './lib/Counter.svelte';
  import Nested from './lib/Nested.svelte'
  import Props from './lib/Props.svelte'
    import SpreadProps from './lib/SpreadProps.svelte';
  import UpdateArray from './lib/UpdateArray.svelte';

  let name = "Svelte";

  let src = svelteLogo
  let htmlString = `<h3>HTML String</h3>`
</script>

<main>
  <h1>Hello {name.toUpperCase()}!</h1>
  <p>My Svelte app</p>
  <img src={svelteLogo} alt="Svelte logo" />
  <img {src} alt="Svelte logo" />
  <Nested />
  <p>{@html htmlString}</p>
  <Counter />
  <UpdateArray />
  <Props />
  <Props answer={42} />
  <SpreadProps />
</main>

<style>
p {
  color: goldenrod;
  font-family: 'Comic Sans MS', cursive;
  font-size: 2em;
}
</style>