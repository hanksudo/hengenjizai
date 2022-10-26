import { SearchBar } from './SearchBar'
import { ProductTable } from './ProductTable'

export const FilterableProductTable = ({ products }) => {
  return (
    <div>
      <SearchBar />
      <ProductTable products={products} />
    </div>
  )
}
