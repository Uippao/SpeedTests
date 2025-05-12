section .rodata
    fmt: db "%d", 10, 0          ; Format string for numbers
    elapsed_fmt: db "Elapsed: %.9f seconds", 10, 0  ; Format string for elapsed time
    const_1e9: dq 1000000000.0   ; 1e9 as double

section .text
global main
extern clock_gettime, printf

main:
    push rbp
    mov rbp, rsp
    push rbx                ; Save callee-saved register
    sub rsp, 48             ; Allocate stack space (maintains 16-byte alignment)

    ; Get start time
    mov edi, 1              ; CLOCK_MONOTONIC
    lea rsi, [rsp]          ; Start timespec (at RSP)
    call clock_gettime

    ; Loop from 1 to 10000
    mov ebx, 1              ; Initialize counter
.print_loop:
    mov rdi, fmt            ; Format string
    mov esi, ebx            ; Current number
    xor eax, eax            ; No vector registers used
    call printf

    inc ebx                 ; Increment counter
    cmp ebx, 10000
    jle .print_loop

    ; Get end time
    mov edi, 1              ; CLOCK_MONOTONIC
    lea rsi, [rsp + 16]     ; End timespec (at RSP + 16)
    call clock_gettime

    ; Calculate elapsed time
    ; Seconds part
    mov rax, [rsp + 16]     ; end.tv_sec
    sub rax, [rsp]          ; - start.tv_sec
    cvtsi2sd xmm0, rax      ; Convert to double

    ; Nanoseconds part
    mov rax, [rsp + 24]     ; end.tv_nsec
    sub rax, [rsp + 8]      ; - start.tv_nsec
    cvtsi2sd xmm1, rax      ; Convert to double
    divsd xmm1, [const_1e9] ; Divide by 1e9
    addsd xmm0, xmm1        ; Add to seconds part

    ; Print elapsed time
    mov rdi, elapsed_fmt    ; Format string
    mov eax, 1              ; 1 vector register used (xmm0)
    call printf

    ; Cleanup and return
    add rsp, 48
    pop rbx
    pop rbp
    xor eax, eax            ; Return 0
    ret
