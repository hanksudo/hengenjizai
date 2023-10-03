<script>
  import svelteLogo from './assets/svelte.svg'
  import Counter from './lib/Counter.svelte';
  import EachBlocks from './lib/EachBlocks.svelte';
  import KeyedEachBlocks from './lib/KeyedEachBlocks.svelte';
  import Nested from './lib/Nested.svelte'
  import Props from './lib/Props.svelte'
  import SpreadProps from './lib/SpreadProps.svelte';
  import UpdateArray from './lib/UpdateArray.svelte';

  let name = "Svelte";

  let src = svelteLogo
  let htmlString = `<h3>HTML String</h3>`
  // get query string
  const urlParams = new URLSearchParams(window.location.search);
  let page = parseInt(urlParams.get('p')) || 1;
</script>

<main>
  <div>
    <button on:click={()=>window.location.href = '?p=1'}>1</button>
    <button on:click={()=>window.location.href = '?p=2'}>2</button>
  </div>

  {#if page === 1}
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
  <EachBlocks />
  <KeyedEachBlocks />
  {:else if page === 2}
  <h1>Page 2</h1>
  {/if}
</main>

<style>
p {
  color: goldenrod;
  font-family: 'Comic Sans MS', cursive;
  font-size: 2em;
}
</style>
