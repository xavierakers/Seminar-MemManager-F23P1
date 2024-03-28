### Memory Management

A memory management packae for storing variable length records

Implements
- Memory Pool
  - Uses buddy method to allocate space for records
  - Returns handle to access records
- Closerd Hash Table
  - Uses double hashing collision prevention
  - Extensible
  - Stores KV pairs
    - Key : Record ID
    - Value : Record location
